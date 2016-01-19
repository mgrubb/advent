(ns advent.day9-test
  (:require [clojure.test :refer :all]
            [advent.day9 :refer :all]
            [loom.graph :as graph]))

#_(defonce weighted-graph (graph/weighted-graph [:london :dublin 464]
                                              [:london :belfast 518]
                                              [:dublin :belfast 141]))
#_(deftest brute-force-test
  (let [bo (brute-force weighted-graph)]
    (is (= (apply min bo) 605))
    (is (= (apply max bo) 982))))

#_(deftest shortest-path-test
  (is (= (shortest-path-length weighted-graph) 605)))

(solution)
