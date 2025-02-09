(ns ice-skating.views
  (:require [hiccup2.core :as h]))

(defmulti view (fn [id _req] id))

(defn render-view [id req]
  (->> (view id req)
       (h/html (h/raw "<!DOCTYPE html>"))
       (str)))

