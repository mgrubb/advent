(ns advent.day7-test
  (:require [clojure.test :refer :all]
            [advent.day7 :refer :all]))

(deftest parse-test
  (is (= (parse "123 -> x") '({:INPUT {:signal 123} :OUTPUT {:wire "x"}})))
  (is (= (parse "wr LSHIFT 2 -> x")
         '({:INPUT [:LSHIFT
                  {:wire "wr"}
                  {:num 2}]
          :OUTPUT {:wire "x"}}))))

(deftest build-tree-test
  (is (= (build-tree (parse "123 -> x"))
         {"x" {:signal 123}}))
  (is (= (build-tree (parse "wr LSHIFT 2 -> x"))
         {"x" [:LSHIFT {:wire "wr"} {:num 2}]})))

(deftest eval-arg-test
  (is (= (eval-arg {:signal 123} {}) 123))
  (is (= (eval-arg {:num 2} {}) 2)))

(deftest do-form-test
  (is (= (do-form [:AND {:signal 123} {:signal 456}] {}) 72)))

(deftest eval-wire-test
  (is (= (eval-wire "x" {"x" [:AND {:signal 123} {:wire "y"}]
                         "y" {:signal 456}}) 72)))

(solution)
