(ns advent.day4
  (:require [advent.solution :refer :all])
  (:import [java.security MessageDigest]
           [java.math BigInteger]))

;; Borrowed from https://gist.github.com/jizhang/4325757
(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defonce ^:dynamic *secret-key* "bgvyzdsv")

(defn mine-coin
  ([len]
   (let [re (re-pattern
              (str "^" (apply str (repeat len \0)) ".*$"))]
     (loop [num 0]
       (let [sum (md5 (str *secret-key* num))]
         (if (re-matches re sum)
           num
           (recur (inc num)))))))
  ([] (mine-coin 5)))

(defsolution 4
  (println "Solution for day 4 part 1 is:" (mine-coin))
  (println "Solution for day 4 part 2 is:" (mine-coin 6)))
