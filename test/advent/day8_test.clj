(ns advent.day8-test
  (:require [clojure.test :refer :all]
            [advent.day8 :refer :all]))

(defonce literal [["\"abc\\x2defcba\""] 14 9])

(deftest code-total-test
  (is (= (code-total (literal 0)) (literal 1))))

(deftest evaled-total-test
  (is (= (evaled-total (literal 0)) (literal 2))))

(deftest code-mem-diff-test
  (is (= (code-mem-diff (literal 0)) (- (literal 1) (literal 2)))))

(solution)
