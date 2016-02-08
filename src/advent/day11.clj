(ns advent.day11
  (:require [advent.solution :refer [defsolution]]))

(def ^:dynamic *alpha-start* (int \a))
(def ^:dynamic *base* 26)

(defn char->num
  [c]
  (- (int c) *alpha-start*))

(defn num->char
  [n]
  (char (+ n *alpha-start*)))

(defn nums->str
  [nc]
  (apply str (map num->char nc)))

;; Thank you slj/advent https://github.com/sjl/advent/blob/master/advent.lisp#L598
(defn inc-nums
  [nums]
  (letfn [(mod+ [x] (mod (inc x) *base*))]
    (reverse (loop [[x & xs] (reverse nums) res []]
               (if x
                 (let [h (mod+ x)]
                   (if (zero? h)
                     (recur xs (conj res h))
                     (concat (conj res h) xs)))
                 (conj res 1))))))

(defn str->nums
  [s]
  (mapv char->num s))

(def ^:dynamic *bad-chars* #{\i \o \l})
(defn no-bad-chars?
  [n]
  (not (some (set (map char->num *bad-chars*)) n)))

(defn has-straight?
  [n]
  (letfn [(is-run? [[x y z]] (= x (dec y) (- z 2)))]
    (->> n
      (partition 3 1)
      (some is-run?)
      some?)))

(defn has-pairs?
  [s]
  (let [xf (comp
             (partition-by identity)
             (map count)
             (filter #{2}))
        cnt (transduce xf + 0 s)]
    (>= cnt 4)))

(defn valid-password?
  [n]
  (and (no-bad-chars? n)
       (has-pairs? n)
       (has-straight? n)))

(defn find-next-password
  ([s]
   (loop [n (-> s str->nums inc-nums)]
     (if (valid-password? n)
       (nums->str n)
       (recur (inc-nums n)))))
  ([] (find-next-password "aabcc")))

(defsolution 11
  (let [nextp (find-next-password "hepxcrrq")]
    (println "Solution for day 11 part 1 is:" nextp)
    (println "Solution for day 11 part 2 is:" (find-next-password nextp))))
