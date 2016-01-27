(ns advent.day11-test
  (:require [clojure.test :refer :all]
            [advent.day11 :refer :all]))

(deftest inc-str-test
  (are [e x] (= e (inc-str x))
       "b" \a
       "c" "b"
       "ba" "z"
       "ya" "xz"))

(deftest no-bad-chars-test
  (are [e x] (= e (no-bad-chars? x))
       true "abcdefgh"
       false "abcdefghi"
       false "jklmn"
       false "mnopqrs"))

(deftest has-straight-test
  (are [e x] (= e (has-straight? x))
       false "abd"
       false "abdegf"
       true "abcdef"))

(deftest has-pairs-test
  (are [e x] (= e (has-pairs? x))
       false "aabcdef"
       false "abcddefg"
       true "aabb"
       true "aabccdee"))

(deftest valid-password-test
  (are [e x] (= e (valid-password? x))
       false "hijaabccdef"
       true "hjaabccdeff"))

(deftest find-next-password-test
  (is (= "aabcce" (find-next-password "aabccd"))))

(solution)
