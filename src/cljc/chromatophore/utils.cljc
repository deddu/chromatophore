(ns chromatophore.utils
  #?(:clj  (:require [schema.core :as schema]))
  #?(:cljs (:require [reagent.ratom :refer [IReactiveAtom]]))
  #?(:cljs (:require-macros [schema.core :as schema]))
  #?(:clj  (:import clojure.lang.IDeref)))

(defn atom?
  "Predicate identifying if an object is an atom"
  [a]
  #?(:cljs (satisfies? IAtom a))
  #?(:clj (= (class a) clojure.lang.Atom)))

(defn deref?
  "Predicate identifying if an object can be dereferenced"
  [a]
  (satisfies? IDeref a))

#?(:cljs
   (defn ratom?
     "Predicate identifying if a object is one of regeant's 'reactive atoms'"
     [a]
     (satisfies? IReactiveAtom a)))

(defn boolean?
  "Predicate identifying if an object is a boolean"
  [x]
  #?(:cljs ((js* "function(x){ return typeof(x) === \"boolean\"; }") x))
  #?(:clj (= (class x) java.lang.Boolean)))

(defmacro fnp
  "A macro for defining functions that take an optional first argument that is a map of parameters"
  [& body]
  (let [[fn-name & fn-body] (if (symbol? (first body))
                              body
                              (cons (gensym "fnp") body))
        fn                  (if-not (->> body flatten (some #(= :- %)))
                              'fn
                              'schema/fn)]
    `(let [fnp# (~fn ~@body)]
       (fn ~fn-name
         [& args#]
         (if (not (map? (first args#)))
           ;; If the first argument isn't a map, make it an empty map
           (apply fnp# {} args#)
           (apply fnp# args#))))))

(defmacro defnp
  "A macro for defining functions that take an optional first argument, that is a map of parameters"
  [fn-name & body]
  (let [metadata (take-while
                  #(not (or (vector? %) (and (list? %) (-> % first vector?))))
                  body)
        fn-body   (drop (count metadata) body)]
    `(def ~fn-name
       ~@metadata
       (with-meta
         (fnp ~fn-name ~@fn-body)
         (meta ~fn-name)))))
