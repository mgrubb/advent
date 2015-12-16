(ns advent.core
  (:require [advent.day1 :as d1]))

(defn -main
  [& args]
  (println "Day 1")
  (println "  Part 1:" (d1/solution))
  (println "  Part 2:" (d1/solution-2)))
