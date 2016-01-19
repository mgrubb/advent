(ns advent.day7
  (:require [advent.solution :refer :all]
            [instaparse.core :as insta]
            [clojure.java.io :as io]))

(def parser
  (insta/parser
    "<S> = CMD*
    CMD = INPUTS <whitespace*> <'->'> <whitespace*> OUTPUT <'\n'*>
    <whitespace> = #'\\s+'
    INPUTS = OP | signal | wire
    <OP> = BINARY_OPS | UNARY_OP
    signal = #'\\d+'
    wire = #'[a-z]+'
    num = #'\\d+'
    INPUT = wire | signal
    <BINARY_OPS> = AND | OR | LSHIFT | RSHIFT
    AND = INPUT <whitespace*> <'AND'> <whitespace*> INPUT
    OR = INPUT <whitespace*> <'OR'> <whitespace*> INPUT
    LSHIFT = INPUT <whitespace*> <'LSHIFT'> <whitespace*> num
    RSHIFT = INPUT <whitespace*> <'RSHIFT'> <whitespace*> num
    <UNARY_OP> = NOT
    NOT = <'NOT'> <whitespace*> INPUT
    OUTPUT = wire"))

(defn transform-tree
  [t]
  (letfn [(str->int
            [tag]
            (fn [x] {tag (Integer/parseInt x)}))
          (str->key
            [tag]
            (fn [x] {tag (keyword x)}))
          (cmd->hash [i o] {:INPUT i :OUTPUT o})
          (rmtag [x] x)
          (to-hash [tag] (fn [x] {tag x}))
          (binary-op [op] (fn [x y] {op [x y]}))]
    (insta/transform {:signal (str->int :signal)
                      :num (str->int :num)
                      :CMD cmd->hash
                      :INPUTS rmtag
                      :OUTPUT rmtag
                      :INPUT rmtag
                      :wire (to-hash :wire)} t)))

(defn parse
  [inp]
  (transform-tree
    (parser inp)))

(defn invert-tree
  [cmd tree]
  (let [out (get-in cmd [:OUTPUT :wire])
        in (:INPUT cmd)]
    (assoc tree out in)))

(defn build-tree
  [cmds]
  (loop [[cmd & cmds] cmds tree {}]
    (if cmd
      (recur cmds (invert-tree cmd tree))
      tree)))

(declare eval-wire)

(def eval-arg
  (memoize (fn [arg tree]
             (cond
               (:wire arg) (eval-wire (:wire arg) tree)
               (:signal arg) (:signal arg)
               (:num arg) (:num arg)))))

(def do-form
  (memoize (fn [form tree]
  (if (sequential? form)
    (let [[cmd arg1 arg2] form
          arg1 (eval-arg arg1 tree)
          arg2 (eval-arg arg2 tree)]
      (cond 
        (= cmd :AND) (bit-and (bit-and arg1 arg2) 0xFFFF)
        (= cmd :OR) (bit-and (bit-or arg1 arg2) 0xFFFF)
        (= cmd :LSHIFT) (bit-and  (bit-shift-left arg1 arg2) 0xFFFF)
        (= cmd :RSHIFT) (bit-and (bit-shift-right arg1 arg2) 0xFFFF)
        (= cmd :NOT) (bit-and (bit-not arg1) 0xFFFF)))
    (eval-arg form tree)))))

(defn eval-wire
  [wire tree]
  (let [form (tree wire)]
    (do-form form tree)))

(defsolution 7
  (let [input (-> "day7-input.txt"
                  io/resource
                  slurp)
        tree (build-tree (parse input))
        wire-a (eval-wire "a" tree)
        tree2 (assoc tree "b" {:signal wire-a})]
    (println "Solution for day 7 part 1 is:" wire-a)
    (println "Solution for day 7 part 2 is:" (eval-wire "a" tree2))))
