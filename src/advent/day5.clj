(ns advent.day5
  (:require [clojure.java.io :as io]))

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

(defn count-nice-words
  [data]
  (loop [[word & words] data sum 0]
    (if word
      (if (nice-word? word)
        (recur words (inc sum))
        (recur words sum))
      sum)))

(defn solution []
  (let [data (line-seq (io/reader (io/resource "day5-input.txt")))]
    (println "Solution for day 5 part 1 is:" (count-nice-words data))))
