(ns ice-skating.views.home
  (:require [ice-skating.views :as v]
            [ice-skating.views.layout :as layout]))

(def aus-flag "🇦🇺")

(defmethod v/view :home [_ _req]
  (layout/root
   {:head [(layout/stylesheet "/css/home.css")]}
   [:main
    [:h1 "Ice Sequence"]
    [:p (str "Welcome to the solution for my (Tim Davis " aus-flag ") lack of friends who want to play add-ons.")]
    [:p "By clicking on start, I will generate a random set of figure skating moves, which are guaranteed to be compatible. No guarantees at the moment for difficulty."]
    [:a {:href "/new" :role "button"} "Start!"]]))


