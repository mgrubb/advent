(ns advent.util)

(defn permutations
  [coll]
  (if (= 1 (count coll))
    (vector coll)
    (vec (for [n coll
               tail (permutations (disj (set coll) n))]
           (vec (cons n tail))))))
