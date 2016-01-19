(ns advent.day5
  (:require [advent.solution :refer :all]
            [clojure.java.io :as io]))

(defonce vowels* #{\a \e \i \o \u})

(defn vowel-count
  [w]
  (count (filter vowels* w)))

(defn double-letter?
  [w]
  (when (re-find #"(.)\1" w) true))

(defn verboten?
  [w]
  (when (re-find #"(?:ab|cd|pq|xy)" w) true))

(defn nice-word?
  [w]
  (every? identity ((juxt #(>= (vowel-count %) 3) double-letter? (complement verboten?)) w)))

(defn letter-pair?
  [w]
  (when (re-find #"(..).*\1" w) true))

(defn split-pair?
  [w]
  (when (re-find #"(.).\1" w) true))

(defn nicer-word?
  [w]
  (and (letter-pair? w)
       (split-pair? w)))

(defn count-nice-words [pred data]
  (count (filter pred data)))

(defsolution 5
  (let [data (line-seq (io/reader (io/resource "day5-input.txt")))]
    (println "Solution for day 5 part 1 is:" (count-nice-words nice-word? data))
    (println "Solution for day 5 part 1 is:" (count-nice-words nicer-word? data))) )
