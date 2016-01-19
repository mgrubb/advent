(ns advent.day2
  (:require [advent.solution :refer :all]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def ^:private parse (comp
                        (map #(str/split % #"x"))
                        (map (fn [x] (map #(Integer/parseInt %) x)))))

(defn gift-area [[l w h]]
  (let [s1 (* 2 l w)
        s2 (* 2 w h)
        s3 (* 2 l h)
        sl (/ (min s1 s2 s3) 2)]
    (+ s1 s2 s3 sl)))

(defn gift-volume
  [[l w h]]
  (* l w h))

(defn ribbon-perimeter
  [[l w h]]
  (let [s1 (+ l l w w)
        s2 (+ w w h h)
        s3 (+ l l h h)]
    (min s1 s2 s3)))

(defn ribbon
  [g]
  (+ (ribbon-perimeter g)
     (gift-volume g)))

(defn ribbons
  ([[g & gs] sum]
   (if g
     (recur gs (+ sum (ribbon g)))
     sum))
  ([gs]
   (ribbons gs 0)))

(defn wrapping-paper
  ([[g & gs] sum]
   (if g
     (recur gs (+ sum (gift-area g)))
     sum))
  ([gs]
   (wrapping-paper gs 0)))

(defn gifts
  [input]
  (->> input
       io/resource
       io/reader
       line-seq
       (transduce parse conj)))



(defsolution 2
  (let [gs (gifts "day2-input.txt")]
    (println "Solution for day 2 part 1 is:" (wrapping-paper gs))
    (println "Solution for day 2 part 2 is:" (ribbons gs))))
