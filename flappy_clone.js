goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.array', 'goog.object', 'goog.string.StringBuffer']);
goog.addDependency("../monet/core.js", ['monet.core'], ['cljs.core']);
goog.addDependency("../monet/canvas.js", ['monet.canvas'], ['cljs.core', 'monet.core']);
goog.addDependency("../flappy_clone/core.js", ['flappy_clone.core'], ['cljs.core', 'goog.dom', 'monet.canvas', 'goog.events']);