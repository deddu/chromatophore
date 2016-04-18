(ns chromatophore.css.screen
  (:require [garden.selectors :refer [attr= before]]
            [garden.stylesheet :refer [at-import]]
            [chromatophore.markdown]
            [chromatophore.svg.stars]))

(def user-select
  [:-webkit-touch-callout
   :-webkit-user-select
   :-khtml-user-select
   :-moz-user-select
   :-ms-user-select
   :user-select])

(def unselectable-css-class
  "A CSS class for unselectable elements"
  [:.unselectable
   (into {} (for [p user-select] [p "none"]))])

(def firefox-empty-contenteditable-workaround
  "A workaround for https://bugzilla.mozilla.org/show_bug.cgi?id=904846
   See http://stackoverflow.com/a/23530317/586893"
  [[((attr= :contenteditable :true) before)
    {:content "'\\200c'"}]])

(def click-to-edit-style
  "Fix for invisible cursor in Chrome.
   See http://stackoverflow.com/a/25898165/586893"
  [:div.click-to-edit {:padding-left  "1px"
                       ;; For symmetry...
                       :padding-right "1px"}])

(def click-icon-to-edit-style
  "A CSS style for click-icon-to-edit components"
  [[:div.click-icon-to-edit
    [:br {:display "none"}]
    [:span.icon
     {:display       "inline-block"
      :border        "1pt solid"
      :border-radius "5pt"
      :margin-right  "5pt"
      :padding-left  "3pt"
      :padding-right "3pt"}]
    [:span.icon.editing
     {:background-color "black"
      :color            "white"}]
    [:div.click-to-edit
     {:display        "inline"
      :vertical-align "top"}]]])

(def unfold-reveal-style
  "A CSS style for an unfold-reveal component"
  [:div.unfold-reveal
   {:user-select "none"}
   [:>
    [:span.deactivated {:opacity 0.1}]
    [:span.toggle {:display       "inline-block"
                   :padding-right "4pt"}]
    [:div.content    {:display        "inline-block"
                      :vertical-align "top"}
     [:> [:div.main {:display "inline"}]]]]])

(defn component-style
  "Extract the style metadata from a component"
  [component]
  (-> component meta :style))

(def style
  [unselectable-css-class
   firefox-empty-contenteditable-workaround
   (component-style #'chromatophore.markdown/md)
   (component-style #'chromatophore.svg.stars/five-star-rating)
   click-to-edit-style
   click-icon-to-edit-style
   unfold-reveal-style])

(comment
  (do (require '[garden.core])
      (garden.core/css [click-to-edit-style])))
