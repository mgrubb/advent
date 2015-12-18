(ns advent.day3
  (:require [clojure.java.io :as io]
            [clojure.set :as set]))

(def ^:private delta-dir
  {\> [1 0]
   \< [-1 0]
   \^ [0 1]
   \v [0 -1]})

(defn move-santa
  [[x y :as loc] dir]
  (let [[dx dy] (delta-dir dir)]
    [(+ x dx) (+ y dy)]))

(defn houses
  [data]
  (let [visited (transient #{})]
    (loop
      [loc [0 0]
       [dir & dirs] data]
      (conj! visited loc)
      (if dir
        (recur (move-santa loc dir) dirs)
        (persistent! visited)))))

(defn visit-houses
  []
  (houses (slurp (io/resource "day3-input.txt"))))

(defn partition-data
  [data]
  [(mapcat identity (partition 1 2 data))
   (mapcat identity (partition 1 2 (drop 1 data)))])

(defn robo-visit-houses
  []
  (let [data (slurp (io/resource "day3-input.txt"))
        [sd rd] (partition-data data)
        sd (houses sd)
        rd (houses rd)]
    (set/union sd rd)))

(defn solution []
  (println "Solution for day 3 part 1 is:" (count (visit-houses)))
  (println "Solution for day 3 part 2 is:" (count (robo-visit-houses))))
