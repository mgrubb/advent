(ns advent.day10
  (:require [advent.solution :refer :all]))


(defonce puzzle-input "1321131112")
(def submap (atom {}))

(defn look-and-say
  [s]
  (apply str (map (fn [part]
                    (if-let [s (@submap part)]
                      s
                      ((swap! submap assoc part (str (count part) (first part))) part)))
                  (partition-by identity s))))

(defn run-puzzle-out
  [input ct]
  (->> input
      (iterate look-and-say)
      (drop ct)
      (take 1)
      first))

(defn run-puzzle
  [input ct]
  (count (run-puzzle-out input ct)))

(defsolution 10
  (let [pi-count (run-puzzle puzzle-input 40)
        pi-count2 (run-puzzle puzzle-input 50)]
    (println "Solution for day 10 part 1 is:" pi-count)
    (println "Solution for day 10 part 2 is:" pi-count2)))
