(ns clojure2json.core
  (:gen-class))

(require '[clojure.pprint :as p])
(require '[clojure.data.json :as json])
(use '[clojure.java.shell :only [sh]])

(defn tv
  ""
  [s]
  (sh "tv" :in s)
  s)

(defn -main
  "I don't do a whole lot ... yet."
  [fp & args]
  (let [code (slurp fp)
        json (json/write-str (read-string
                              (tv code)
                              ;; code
                              ))]
    ;; (p/pprint json)
    (print json)))
