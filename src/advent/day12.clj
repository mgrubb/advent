(ns advent.day12
  (:require [clojure.java.io :as io]
            [clojure.walk :refer [postwalk]]
            [clojure.data.json :as json]
            [advent.solution :refer [defsolution]]))

(defn read-input
  [f]
  (-> f
      io/resource
      io/reader
      (json/read :key-fn keyword)))

(defn sum-walk
  [d]
  (let [res (volatile! 0)]
    (postwalk #(if (number? %)
                 (vswap! res + %)
                 %) d)
    @res))

(defsolution 12
  (show 1 (sum-walk (read-input "day12-input.txt"))))

