(ns advent.day1
  (:require [clojure.java.io :as io]))

(defn- do-char
  [c s]
  (condp = c
    \( (inc s)
    \) (dec s)
    s)) 

(defn floor
  "Counts parens to go up or down"
  [s]
  (loop [[h & t] s sum 0]
    (if h
      (recur t (do-char h sum))
      sum)))

(defn basement
  "Returns the position which sends us to the basement for the first time."
  [s]
  (loop [[h & t] s sum 0 pos 0]
    (if h
      (if (= sum -1)
        pos
        (recur t (do-char h sum) (inc pos)))
      (when (< sum 0)
        pos))))

(defn solution
  []
  (println "Solution for day 1 part 1 is:" (floor (slurp (io/resource "day1-input.txt"))))
  (println "Solution for day 1 part 2 is:" (basement (slurp (io/resource "day1-input.txt")))))
