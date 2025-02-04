(ns user
  (:require
   [ice-skating.system :refer [config]]
   [integrant.core :as ig]
   [integrant.repl :refer [go halt prep init reset]]))

(integrant.repl/set-prep! #(ig/deprofile config [:dev]))

(comment
  (prep)
  (init)
  (go)
  (halt)
  (reset))
