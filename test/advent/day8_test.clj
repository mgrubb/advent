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

(deftest encode-test
  (let [empty-code "\"\"" ; <- ""
        empty-code-expected "\"\\\"\\\"\""
        empty-code-encoded (encode empty-code)
        empty-code-count (count empty-code-encoded)

        abc "\"abc\"" ; <- "abc"
        abc-expected "\"\\\"abc\\\"\""
        abc-encoded (encode abc)
        abc-count (count abc-encoded)
        
        aaa "\"aaa\\\"aaa\""
        aaa-expected "\"\\\"aaa\\\\\\\"aaa\\\"\""
        aaa-encoded (encode aaa)
        aaa-count (count aaa-encoded)]
    (is (= empty-code-encoded empty-code-expected))
    (is (= empty-code-count 6))

    (is (= abc-encoded abc-expected))
    (is (= abc-count 9))

    (is (= aaa-encoded aaa-expected))
    (is (= aaa-count 16))))

(deftest code-recode-diff-test
  (let [lines ["\"\""
               "\"abc\""  
               "\"aaa\\\"aaa\""
               "\"\\x27\""]]
    (is (= (code-total lines) 23))
    (is (= (reencode-total lines) 42))))
(solution)
