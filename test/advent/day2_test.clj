(ns advent.day2-test
  (:require [clojure.test :refer :all]
            [advent.day2 :refer :all]))

(defonce wrapping-data [[2 3 4]
                        [1 1 10]
                        [4 6 2]])

(deftest gift-area-test
  (is (= (gift-area (nth wrapping-data 0)) 58))
  (is (= (gift-area (nth wrapping-data 1)) 43))
  (is (= (gift-area (nth wrapping-data 2)) 96)))

(deftest wrapping-paper-total-test
  (is (= (wrapping-paper wrapping-data) 197)))

(deftest ribbon-perimeter-test
  (is (= (ribbon-perimeter (nth wrapping-data 0)) 10))
  (is (= (ribbon-perimeter (nth wrapping-data 1)) 4))
  (is (= (ribbon-perimeter (nth wrapping-data 2)) 12)))

(deftest ribbon-test
  (is (= (ribbon (nth wrapping-data 0)) 34))
  (is (= (ribbon (nth wrapping-data 1)) 14))
  (is (= (ribbon (nth wrapping-data 2)) 60)))

(deftest ribbons-test
  (is (= (ribbons wrapping-data) 108)))

(solution)
