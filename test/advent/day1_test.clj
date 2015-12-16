(ns advent.day1-test
  (:require [clojure.test :refer :all]
            [advent.day1 :refer :all]))

(deftest floor-0
  (is (= (floor "") 0))
  (is (= (floor nil) 0))
  (is (= (floor "(())") 0))
  (is (= (floor "()()") 0)))

(deftest floor-3
  (is (= (floor "(((") 3))
  (is (= (floor "(()(()(")))
  (is (= (floor "))((((("))))

(deftest floor--1
  (is (= (floor "())") -1))
  (is (= (floor "))(") -1)))

(deftest floor--3
  (is (= (floor ")))") -3))
  (is (= (floor ")())())"))))

(deftest floor-invalidchar
  (is (= (floor ") ) )") -3))
  (is (= (floor "(a)b)") -1)))

(println "Solution for day1 part 1 is:" (solution))

