(ns advent.day1
  (:require [clojure.java.io :as io]))

(defn floor
  "Counts parens to go up or down"
  [s]
  (letfn
    [(do-char [c s]
      (condp = c
        \( (inc s)
        \) (dec s)
        s))]
    (loop [[h & t] s sum 0]
      (if h
        (recur t (do-char h sum))
        sum))))

(defn solution
  []
  (floor (slurp (io/resource "day1-input.txt"))))
