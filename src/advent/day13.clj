(ns advent.day13
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [advent.solution :refer [defsolution]]
            [advent.util :refer [permutations]]))

(defn weight-map
  [data]
  (reduce
    #(update %1 (nth %2 0) assoc (nth %2 1) (nth %2 2))
    {} data))

(defn- split-line
  [l]
  (mapv (vec (str/split (str/replace l #"\." "") #" ")) [0 2 3 10]))

(defn- parse-line
  [l]
  (let [[a op n b] (split-line l)
        op (if (= op "lose") -1 1)
        n (Integer/parseInt n)
        n (* n op)]
    [a b n]))

(defn read-input
  [f]
  (->> f
       io/resource
       io/reader
       line-seq
       (map parse-line)
       vec))

(def weight
  (memoize (fn [wm p1 p2]
             (get-in wm [p1 p2]))))

(def weight-sum
  (memoize (fn ws
             ([wm [p1 p2]]
                   (+ (weight wm p1 p2)
                      (weight wm p2 p1)))
             ([wm p1 p2] (ws wm [p1 p2])))))

(defn all-seatings
  [guests]
  (map
    #(partition 2 1 (conj % (first %)))
    (permutations guests)))

(defn add-me
  [wm]
  (loop [wm wm [k & ks] (keys wm)]
    (if k
      (recur (-> wm
                 (assoc-in [k "Me"] 0)
                 (assoc-in ["Me" k] 0))
             ks)
      wm)))

(defn find-best-seating
  [wm]
  (let [seatings (all-seatings (keys wm))]
    (apply max (map
                 (fn [pl] (reduce #(+ %1 (weight-sum wm %2)) 0 pl))
                 seatings))))

(defsolution 13
  (let [wm (weight-map (read-input "day13-input.txt"))
        wm-me (add-me wm)
        p1 (find-best-seating wm)
        p2 (find-best-seating wm-me)]
    (show 1 p1)
    (show 2 p2)))
