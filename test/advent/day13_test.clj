(ns advent.day13-test
  (:require [clojure.test :refer :all]
            [advent.day13 :refer :all]))



(defonce wm {:me {:you 1}
             :you {:me 2}})

(deftest weight-test
  (is (= 1 (weight wm :me :you)))
  (is (= 2 (weight wm :you :me))))

(deftest weight-sum-test
  (is (= 3 (weight-sum wm :me :you)))
  (is (= 3 (weight-sum wm :you :me))))

(solution)
