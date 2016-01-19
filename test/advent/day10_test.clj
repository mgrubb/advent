(ns advent.day10-test
  (:require [clojure.test :refer :all]
            [advent.day10 :refer :all]))


(deftest look-say-test
  (are [i e] (= e (look-and-say i))
       "1" "11"
       "11" "21"
       "21" "1211"
       "1211" "111221"
       "111221" "312211"))

(solution false)
