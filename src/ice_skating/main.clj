(ns ice-skating.main
  (:require [ice-skating.system :as system])
  (:gen-class))

(defn -main []
  (let [s (system/start-system)]
    (.addShutdownHook
     (Runtime/getRuntime)
     (Thread. #(system/stop-system s)))))
