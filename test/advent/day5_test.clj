(ns advent.day5-test
  (:require [clojure.test :refer :all]
            [advent.day5 :refer :all]))


(deftest vowel-count-test
  (is (= (vowel-count "aeiou") 5))
  (is (= (vowel-count "Too True") 4))
  (is (= (vowel-count "ebcdic") 2))
  (is (= (vowel-count "2 crzy 4 wrds") 0)))

(deftest double-letter?-test
  (is (not (double-letter? "abcdefg")))
  (is (double-letter? "abbcfeg")))

(deftest verboten?-test
  (is (not (verboten? "Hello World")))
  (is (verboten? "abcdefg"))
  (is (verboten? "mind your pqs")))

(deftest nice-word?-test
  (are [x] (nice-word? x)
       "ugknbfddgicrmopn"
       "aaa"
       "baaa")
  (are [x] (not (nice-word? x))
       "jchzalrnumimnmhp"
       "haegwjzuvuyypxyu"
       "dvszwmarrgswjxmb"))

(deftest nicer-word?-test
  (are [x] (nicer-word? x)
       "qjhvhtzxzqqjkmpb"
       "xxyxx")
  (are [x] (not (nicer-word? x))
       "uurcxstgmygtbstg"
       "ieodomkazucvgmuy"))

(deftest nice-word-count
  (is (= (count-nice-words nice-word? ["ugknbfddgicrmopn"
                                       "aaa"
                                       "a"
                                       "baaa"]) 3)))
(solution)
