(ns clojure2json.core
  (:gen-class))

(require '[clojure.pprint :as p])
(require '[clojure.data.json :as json])
(use '[clojure.java.shell :only [sh]])

;; This appears to hang
(defn tv
  ""
  [s]
  (sh "tv" :in s)
  s)

;; (defn pprint-to-string
;;   ""
;;   [o]
;;   (with-out-str (clojure.pprint/pprint o )))
;; (def pps pprint-to-string)

(defn -main
  "I don't do a whole lot ... yet."
  [fp & args]
  (let [code (slurp fp)
        ;; pp (p/pprint
        ;;     (tv code)
        ;;     code)
        json (json/write-str (read-string
                              ;; (tv code)
                              code))]
    (p/pprint json)
    (print json)))
