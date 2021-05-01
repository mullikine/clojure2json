(ns clojure2json.core
  (:gen-class))

(require '[clojure.pprint :as p])

;; this method wasn't capuring the entire file
;; Probably not it's fault, but mine, though
(require '[clojure.data.json :as json])
(use '[clojure.java.shell :only [sh]])



; Context:
; http://stackoverflow.com/questions/3436216/how-to-map-clojure-code-to-and-from-json

(defn escape-string [x]
  (clojure.string/replace x #"^[':\\]" "\\\\$0"))

(defn code-to-json [x]
  (condp #(%1 %2) x
    number?  x
    symbol?  (str \' (name x))
    keyword? (str \: (name x))
    string?  (escape-string x)
    list?    (into [] (cons "list"   (map code-to-json x)))
    vector?  (into [] (cons "vector" (map code-to-json x)))
    set?     (into [] (cons "set"    (map code-to-json x)))
    map?     (into {} (map #(mapv code-to-json %) x))
    (throw (Exception. (format "Unsupported type: %s" (type x))))))



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
        ;; json (json/write-str (read-string
        ;;                       code))
        ; json (tv code)
        ;; json (code-to-json (read-string
        ;;                       (str "(" code ")")))
        json (json/write-str (read-string
                              ;; (tv code)
                              (str "(" code ")")))
        ]
    (p/pprint json)
    ; (print json)
    ))
