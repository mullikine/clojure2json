(ns clojure2json.core
  (:gen-class))

(require '[clojure.pprint :as p])
(require '[clojure.data.json :as json])

(defn -main
  "I don't do a whole lot ... yet."
  [fp & args]
  (let [code (slurp fp)
        json (json/write-str (read-string code)]
    ;; (p/pprint json)
        (print json)))
