(ns advent.day15-test
  (:require [clojure.test :refer :all]
            [advent.day15 :refer :all]))

(deftest make-cookie-test
  (let [ingredients [{:capacity -1
                      :durability -2
                      :flavor 6
                      :texture 3
                      :calories 8}
                     {:capacity 2
                      :durability 3
                      :flavor -2
                      :texture -1
                      :calories 3}
                     {:capacity -1
                      :durability -1
                      :flavor -1
                      :texture -1
                      :calories -1}
                     {:capacity -1
                      :durability -1
                      :flavor -1
                      :texture -1
                      :calories -1}]
        recipe [44 56]]
    (is (= 62842880 (make-cookie (take 2 ingredients) recipe)))
    (is (= 0 (make-cookie (drop 2 ingredients) recipe)))))

(solution)
