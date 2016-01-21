(ns advent.day9-test
  (:require [clojure.test :refer :all]
            [advent.day9 :refer :all]
            [loom.graph :as graph]))

(defonce input [[:london :dublin 464]
                [:london :belfast 518]
                [:dublin :belfast 141]])

(deftest mkdisthash-test
  (is (= (mkdisthash input) {[:london :dublin] 464
                             [:dublin :london] 464
                             [:london :belfast] 518
                             [:belfast :london] 518
                             [:dublin :belfast] 141
                             [:belfast :dublin] 141})))
(deftest nodes-test
  (is (= (nodes (mkdisthash input)) #{:london :dublin :belfast})))

(deftest all-paths-test
  (let [paths (set (all-paths (nodes (mkdisthash input))))]
    (are [x] (not (nil? (paths x)))
         [:london :dublin :belfast]
         [:london :belfast :dublin]
         [:dublin :london :belfast]
         [:dublin :belfast :london]
         [:belfast :dublin :london]
         [:belfast :london :dublin])))

(deftest split-path-test
  (is (= (split-path [:a :b :c]) [[:a :b] [:b :c]])))

(deftest path-length-test
  (let [dmap (mkdisthash input)
        path [:london :dublin :belfast]]
    (is (= (path-length dmap path)  605))))

(solution)
