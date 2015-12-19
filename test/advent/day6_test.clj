(ns advent.day6-test
  (:require [clojure.test :refer :all]
            [advent.day6 :refer :all]))

(def ^:dynamic *grid* nil)

(defn each-fixture [f]
  (binding [*grid* (make-grid 5)]
    (f)))

(use-fixtures :each each-fixture)

(deftest coord->idx-test
  (are [x y] (= (coord->idx *grid* x) y)
       [0 0] 0
       [0 1] 5
       [1 0] 1
       [2 0] 2
       [2 2] 12
       [1 1] 6
       [4 4] 24))

(deftest range->idxs-test
  (is (= (range->idxs *grid* [0 0] [2 2]) [0 1 2 5 6 7 10 11 12])))

(deftest parse-command-test
  (are [x y] (= (parse-command x) y)
       "turn on 66,50 through 579,301" {:op :on :p1 [66 50] :p2 [579 301]}
       "turn off 997,667 through 997,670" {:op :off :p1 [997 667] :p2 [997 670]}
       "toggle 914,643 through 975,840" {:op :toggle :p1 [914 643] :p2 [975 840]}))

(deftest do-command-text
  (are [x y] (is (= (do-command *grid* x) y))
       {:op :on :p1 [0 0] :p2 [4 4]} (vec (repeat 25 1))
       {:op :toggle :p1 [0 0] :p2 [2 0]} (vec (concat [1 1 1] (repeat 22 0)))))

(solution)
