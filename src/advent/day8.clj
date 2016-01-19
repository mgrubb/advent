(ns advent.day8
  (:require [advent.solution :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn encode
  [s]
  (let [s (-> s
              (str/replace "\\" "\\\\")
              (str/replace "\"" "\\\""))]
    (str "\"" s "\"")))

(defn code-total
  [lines]
  (transduce (map count) + 0 lines))

(defn reencode-total
  [lines]
  (transduce (comp (map encode) (map count)) + 0 lines))

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

(defn code-recode-diff
  [lines]
  (- (reencode-total lines)
     (code-total lines)))

(defsolution 8
  (let [lines (->> "day8-input.txt"
                   io/resource
                   io/reader
                   line-seq)
        n (code-mem-diff lines)
        m (code-recode-diff lines)]
    (println "Solution for day 8 part 1 is:" n)
    (println "Solution for day 8 part 2 is:" m)))
