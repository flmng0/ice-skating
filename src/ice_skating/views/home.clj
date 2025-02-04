(ns ice-skating.views.home
  (:require [ice-skating.views :as v]))

(defmethod v/view :home [_ _req]
  [:p "This is a test"])



