(ns advent.day14
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]
    [advent.solution :refer :all]))

(defn- parse-line
  [l]
  (let [parts (map (vec (str/split l #" ")) [3 6 13])]
    (map #(Integer/parseInt %) parts)))

(defn read-input
  [f]
  (->> f
      io/resource
      io/reader
      line-seq
      (map parse-line)))

(defn run-reindeer
  [race-length speed rate stop]
  (* speed (reduce + (take race-length
                  (cycle
                    (concat (repeat rate 1)
                            (repeat stop 0)))))))

(defn mkdeer
  [race-length rate run-time rest-time]
  {:rate rate
   :distance 0
   :score 0
   :state (take race-length (cycle (concat (repeat run-time 1)
                                           (repeat rest-time 0))))})

(defn tick
  [deers]
  (for [{:keys [rate distance state] :as deer} deers]
    (assoc deer
           :distance (+ distance (* rate (first state)))
           :state (rest state))))

(defn score-deer
  [deers]
  (let [high (apply max (map :distance deers))]
    (map #(if (= high (:distance %))
            (update % :score inc)
            %) deers)))

(defn run-race
  [data len]
  (apply max
         (map (partial apply run-reindeer len) data)))

(defn run-race2
  [data len]
  (let [deers (map (partial apply mkdeer len) data)]
    (loop [i (range len) deers deers]
      (if (seq i)
        (recur (rest i) (-> deers tick score-deer))
        (apply max (map :score deers))))))

(defsolution 14
  (let [data (read-input "day14-input.txt")]
    (show 1 (run-race data 2503))
    (show 2 (run-race2 data 2503))))
