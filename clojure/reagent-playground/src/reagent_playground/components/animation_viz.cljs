(ns reagent-playground.components.animation-viz
  (:require [reagent-playground.session :as session]))

(defn column
  [x]
  [:div.column
   (for [y (range (session/get :size))]
     (if (true? (session/get-in [:animation-state x y :highlighted]))
       [:div.box.highlighted]
       [:div.box]))])

(defn component
  []
  (when (session/get-in [:components :animation :visible])
    [:div
     (for [x (range (session/get :size))]
       (column x))]))
