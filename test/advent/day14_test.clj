(ns advent.day14-test
  (:require [clojure.test :refer :all]
            [advent.day14 :refer :all]))

(deftest run-reindeer-test
  (are [e x y z] (= e (run-reindeer 1000 x y z))
       1120   14 10 127
       1056   16 11 162))

(deftest tick-test
  (is (= 14 (:distance (first (tick [(mkdeer 1 14 10 127)]))))))
(solution)
