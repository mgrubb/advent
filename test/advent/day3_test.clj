(ns advent.day3-test
  (:require [clojure.test :refer :all]
            [advent.day3 :refer :all]))


(deftest move-santa-test
  (is (= (move-santa [0 0] \>) [1 0]))
  (is (= (move-santa [0 0] \<) [-1 0]))
  (is (= (move-santa [0 0] \^) [0 1]))
  (is (= (move-santa [0 0] \v) [0 -1]))
  (is (= (move-santa [3 4] \>) [4 4]))
  (is (= (move-santa [3 4] \<) [2 4]))
  (is (= (move-santa [3 4] \^) [3 5]))
  (is (= (move-santa [3 4] \v) [3 3])))

(deftest houses-test
  (is (= (houses "<^>v")
         #{[0 0] [-1 0] [-1 1] [0 1]})))

(deftest multi-houses-test
  (is (= (houses (mapcat identity (partition 1 2 "<^>v")))
         #{[0 0] [-1 0]}))
  (is (= (houses (mapcat identity (partition 1 2 (drop 1 "<^>v"))))
         #{[0 0] [0 1]})))
(solution)
