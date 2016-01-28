(ns advent.day12-test
  (:require [clojure.test :refer :all]
            [advent.day12 :refer :all]))


(deftest sum-walk-test
  (is (= (+ 12 13 1 2 3 4 5 6 7 8 9)
         (sum-walk [{:a 12 :b {:c 13} :d [1 2 3]} [4 5 6] [7 {:e 8 :f [9]}]]))))

(solution)
