(ns advent.day8
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


(defn code-total
  [lines]
  (transduce (map count) + 0 lines))

(defn fix-escapes
  [s]
  (str/replace s #"\\x([0-9a-f][0-9a-f])" "\\\\u00$1"))

(defn evaled-total
  [lines]
  (let [xf (comp (map fix-escapes)
                 (map read-string)
                 (map count))]
    (transduce xf + 0 lines)))

(defn code-mem-diff
  [lines]
  (- (code-total lines)
     (evaled-total lines)))

(defn solution
  []
  (let [lines (->> "day8-input.txt"
                   io/resource
                   io/reader
                   line-seq)
        n (code-mem-diff lines)]
    (println "Solution for day 8 part 1 is:" n)))
