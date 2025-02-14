(ns status-im.contexts.wallet.home.tabs.assets.style
  (:require [status-im.contexts.shell.constants :as constants]))

(def list-container
  {:padding-horizontal 8
   :padding-bottom     constants/floating-shell-button-height})

(def buy-and-receive-cta-container
  {:flex-direction     :row
   :padding-horizontal 20
   :padding-vertical   8})

(def cta-card
  {:flex 1})
