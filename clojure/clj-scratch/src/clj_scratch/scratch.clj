(ns clj-scratch.scratch
  (:use [clj-scratch.core]))

;;scratch...
(defn non-side-effecty-function [n]
  (cond
   (neg? n)
   (* n -1)

   (= n 0)
    1

    :else
    (* n 1.5)))

(defn side-effecty-function [n]
  (do (println (str "doing stuff => " n))
      (non-side-effecty-function n)))

(defn exit-pred [n]
  (> n 10))

(defn exit-pred-two [n]
  (> n 100))


(comment
  (loop-state-until-done side-effecty-function 50 -0.5 exit-pred)
  )
