(ns advent.day4-test
  (:require [clojure.test :refer :all]
            [advent.day4 :refer :all]))

(deftest md5-test
  (is (= (md5 *secret-key*) "bbd0b4dcb0d07a947bf3c280f99baffd")))

(solution)
