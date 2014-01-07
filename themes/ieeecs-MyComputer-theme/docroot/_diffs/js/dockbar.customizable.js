
// ---------------------------------------
// Customizable Dockbar
// ---------------------------------------
AUI().add(
	'liferay-dockbar-custom',
	function(A) {
		var LayoutConfiguration = Liferay.LayoutConfiguration;
		var Portlet = Liferay.Portlet;
		var Util = Liferay.Util;

		var BODY = A.getBody();

		var DockbarCustom = {
			init: function() {
				var instance = this;

				var dockBar = A.one('#dockbar');

				if (dockBar) {
					instance.dockBar = dockBar;

					instance._namespace = dockBar.attr('data-namespace');

					Liferay.once('initDockbar', instance._init, instance);

					var eventHandle = dockBar.on(
						['focus', 'mousemove', 'touchstart'],
						function(event) {
							Liferay.fire('initDockbar');

							eventHandle.detach();
						}
					);

					BODY.addClass('dockbar-ready');
				}
			},

			addItem: function(options) {
				var instance = this;

				if (options.url) {
					options.text = '<a href="' + options.url + '">' + options.text + '</a>';
				}

				var item = A.Node.create('<li class="' + (options.className || '') + '">' + options.text + '</li>');

				instance.dockBar.one('> ul').appendChild(item);

				instance._toolbarItems[options.name] = item;

				return item;
			},

			addMenu: function(options) {
				var instance = this;

				var menu;
				var name = options.name;

				if (name && A.one(options.trigger)) {

					delete options.name;

					options.zIndex = instance.menuZIndex++;

					A.mix(
						options,
						{
							hideDelay: 500,
							hideOn: 'mouseleave',
							showOn: 'mouseover'
						}
					);

					var boundingBox = options.boundingBox;

					if (boundingBox && !('contentBox' in options)) {
						options.contentBox = boundingBox + '> .aui-menu-content';
					}

					menu = new A.OverlayContext(options);

					var contentBox = menu.get('contentBox');

					contentBox.plug(
						A.Plugin.NodeFocusManager,
						{
							circular: false,
							descendants: 'a',
							focusClass: 'aui-focus',
							keys: {
								next: 'down:40',
								previous: 'down:38'
							}
						 }
					);

					var focusManager = contentBox.focusManager;

					contentBox.all('li').addClass('aui-menu-item');

					contentBox.delegate(
						'mouseenter',
						function (event) {
							focusManager.focus(event.currentTarget.one('a'));
						},
						'.aui-menu-item'
					);

					contentBox.delegate(
						'mouseleave',
						function (event) {
							focusManager.blur(event.currentTarget.one('a'));
						},
						'.aui-menu-item'
					);

					var MenuManager = DockbarCustom.MenuManager;

					var dockBar = instance.dockBar;

					var trigger = menu.get('trigger').item(0);
					var button = trigger.one('a');

					MenuManager.register(menu);

					menu.on(
						'visibleChange',
						function(event) {
							var visible = event.newVal;

							if (visible) {
								MenuManager.hideAll();
							}

							trigger.toggleClass('menu-button-active', visible);
						}
					);

					button.on(
						'focus',
						function(event) {
							menu.show();
						}
					);

					button.on(
						'keydown',
						function(event) {
							if (event.keyCode == 40) {
								focusManager.focus(0);
							}
						}
					);

					menu.on(
						'keydown',
						function(event) {
							if (focusManager.get('activeDescendant') == -1) {
								button.focus();
							}
							else {
								instance._updateMenu(event.domEvent, button);
							}
						}
					);

					instance[name] = menu.render(instance.dockBar);
				}

				return menu;
			},

			addMessage: function(message, messageId) {
				var instance = this;

				var messages = instance.messages;

				if (!instance.messageList) {
					instance.messageList = [];
					instance.messageIdList = [];
				}

				messages.show();

				if (!messageId) {
					messageId = A.guid();
				}

				instance.messageList.push(message);
				instance.messageIdList.push(messageId);

				var currentBody = messages.get('bodyContent');

				message = instance._createMessage(message, messageId);

				messages.setStdModContent('body', message, 'after');

				var messagesContainer = messages.get('boundingBox');

				var action = 'removeClass';

				if (instance.messageList.length > 1) {
					action = 'addClass';
				}

				messagesContainer[action]('multiple-messages');

				return messageId;
			},

			addUnderlay: function(options) {
				var instance = this;

				var autoShow = true;

				var underlay;
				var name = options.name;

				if (name) {
					autoShow = options.visible !== false;

					underlay = instance[name];

					if (!underlay) {
						delete options.name;

						options.zIndex = instance.underlayZIndex++;

						options.align = options.align || {
							node: instance.dockBar,
							points: ['tl', 'bl']
						};

						underlay = new instance.Underlay(options);

						underlay.render(instance.dockBar);

						var ioOptions = options.io;

						if (ioOptions) {
							ioOptions.loadingMask = {
								background: 'transparent'
							};

							underlay.plug(A.Plugin.IO, ioOptions);
						}

						instance[name] = underlay;
					}

					if (autoShow && underlay && underlay instanceof A.Overlay) {
						underlay.show();
					}
				}

				return underlay;
			},

			clearMessages: function(event) {
				var instance = this;

				instance.messages.set('bodyContent', ' ');

				instance.messageList = [];
				instance.messageIdList = [];
			},

			setMessage: function(message, messageId) {
				var instance = this;

				var messages = instance.messages;

				if (!messageId) {
					messageId = A.guid();
				}

				instance.messageList = [message];
				instance.messageIdList = [messageId];

				messages.show();

				message = instance._createMessage(message, messageId);

				messages.set('bodyContent', message);

				var messagesContainer = messages.get('boundingBox');

				messagesContainer.removeClass('multiple-messages');

				return messageId;
			},

			_createMessage: function(message, messageId) {
				var instance = this;

				var cssClass = '';

				if (instance.messageList.length == 1) {
					cssClass = 'first';
				}

				return '<div class="dockbar-message ' + cssClass + '" id="' + messageId + '">' + message + '</div>';
			},

			_init: function() {
				var instance = this;

				var dockBar = instance.dockBar;
				var namespace = instance._namespace;

				var MenuManager = new A.OverlayManager(
					{
						zIndexBase: 100000
					}
				);

				var UnderlayManager = new A.OverlayManager(
					{
						zIndexBase: 300
					}
				);

				DockbarCustom.MenuManager = MenuManager;
				DockbarCustom.UnderlayManager = UnderlayManager;

				instance._toolbarItems = {};

				var messages = instance.addUnderlay(
					{
						align: {
							node: instance.dockBar,
							points: ['tc', 'bc']
						},
						bodyContent: '',
						boundingBox: '#' + namespace + 'dockbarMessages',
						header: 'My messages',
						name: 'messages',
						visible: false
					}
				);

				messages.on(
					'visibleChange',
					function(event) {
						if (event.newVal) {
							A.getBody().addClass('showing-messages');

							MenuManager.hideAll();
						}
						else {
							A.getBody().removeClass('showing-messages');
						}
					}
				);

				messages.closeTool.on('click', instance.clearMessages, instance);

				var addContent = instance.addMenu(
					{
						boundingBox: '#' + namespace + 'addContentContainer',
						name: 'addContent',
						trigger: '#' + namespace + 'addContent'
					}
				);

				if (addContent) {
					addContent.on(
						'show',
						function() {
							Liferay.fire('initLayout');
							Liferay.fire('initNavigation');
						}
					);

					var addContentNode = addContent.get('contentBox');

					instance._addContentNode = addContentNode;

					var commonItems = addContentNode.one('.common-items');

					if (commonItems) {
						commonItems.removeClass('aui-menu-item');
					}

					addContentNode.delegate(
						'click',
						function(event) {
							event.halt();

							var item = event.currentTarget;

							if (item.hasClass('lfr-portlet-used')) {
								return;
							}

							var portletId = item.attr('data-portlet-id');

							if (!item.hasClass('lfr-instanceable')) {
								instance._toggleAppShortcut(item, true);
							}

							Portlet.add(
								{
									portletId: portletId
								}
							);

							if (!event.shiftKey) {
								MenuManager.hideAll();
							}
						},
						'.app-shortcut'
					);

					addContentNode.focusManager.set('descendants', 'a:not(.lfr-portlet-used)');

					Liferay.on(
						'closePortlet',
						function(event) {
							var item = addContentNode.one('.app-shortcut[data-portlet-id=' + event.portletId + ']');

							if (item) {
								instance._toggleAppShortcut(item, false);
							}
						}
					);
				}

				var manageContent = instance.addMenu(
					{
						boundingBox: '#' + namespace + 'manageContentContainer',
						name: 'manageContent',
						trigger: '#' + namespace + 'manageContent'
					}
				);

				instance.addMenu(
					{
						boundingBox: '#' + namespace + 'myPlacesContainer',
						name: 'myPlaces',
						trigger: '#' + namespace + 'myPlaces'
					}
				);

				var userOptionsContainer = A.one('#' + namespace + 'userOptionsContainer');

				if (userOptionsContainer) {
					instance.addMenu(
						{
							boundingBox: userOptionsContainer,
							name: 'userOptions',
							trigger: '#' + namespace + 'userAvatar'
						}
					);
				}

				var isStaging = BODY.hasClass('staging') || BODY.hasClass('remote-staging');
				var isLiveView = BODY.hasClass('live-view');

				if (isStaging || isLiveView) {
					instance.addMenu(
						{
							boundingBox: '#' + namespace + 'stagingContainer',
							name: 'staging',
							trigger: '#' + namespace + 'staging'
						}
					);
				}

				var addApplicationLink = A.one('#' + namespace + 'addApplication');

				if (addApplicationLink) {
					addApplicationLink.on(
						'click',
						function(event) {
							addContent.hide();

							var addApplication = DockbarCustom.addApplication;

							if (!addApplication) {
								var setAddApplicationUI = function(visible) {
									BODY.toggleClass('lfr-has-sidebar', visible);
								};

								addApplication = instance.addUnderlay(
									{
										after: {
											render: function(event) {
												setAddApplicationUI(true);
											}
										},
										className: 'add-application',
										io: {
											after: {
												success: DockbarCustom._loadAddApplications
											},
											data: {
												doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
												p_l_id: themeDisplay.getPlid(),
												p_p_id: 87,
												p_p_state: 'exclusive'
											},
											uri: themeDisplay.getPathMain() + '/portal/render_portlet'
										},
										name: 'addApplication',
										width: '255px'
									}
								);

								addApplication.after(
									'visibleChange',
									function(event) {
										if (event.newVal) {
											Util.focusFormField('#layout_configuration_content');
										}

										setAddApplicationUI(event.newVal);
									}
								);
							}
							else {
								addApplication.show();
							}

							addApplication.focus();
						}
					);
				}

				var pageTemplate = A.one('#pageTemplate');

				if (pageTemplate) {
					pageTemplate.on(
						'click',
						function(event) {
							manageContent.hide();

							var manageLayouts = DockbarCustom.manageLayouts;

							if (!manageLayouts) {
								manageLayouts = instance.addUnderlay(
									{
										className: 'manage-layouts',
										io: {
											data: {
												doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
												p_l_id: themeDisplay.getPlid(),
												redirect: Liferay.currentURL
											},
											uri: themeDisplay.getPathMain() + '/layout_configuration/templates'
										},
										name: 'manageLayouts',
										width: '800px'
									}
								);
							}
							else {
								manageLayouts.show();
							}

							manageLayouts.focus();
						}
					);
				}

				dockBar._menuButtons = dockBar.all('ul.aui-toolbar > li > a, .user-links a, .sign-out a');

				dockBar.delegate(
					'keydown',
					function(event) {
						instance._updateMenu(event, event.currentTarget);
					},
					'.aui-toolbar a'
				);
			},

			_toggleAppShortcut: function(item, force) {
				var instance = this;

				item.toggleClass('lfr-portlet-used', force);

				instance._addContentNode.focusManager.refresh();
			},

			_updateMenu: function(event, item) {
				var instance = this;

				var menuButtons = instance.dockBar._menuButtons;
				var lastButtonIndex = menuButtons.size();
				var index = menuButtons.indexOf(item);

				if (index > -1) {
					var button;

					var keyCode = event.keyCode;

					if (keyCode == 37 && index > 0) {
						button = menuButtons.item(--index);
					}
					else if (keyCode == 39 && (index < lastButtonIndex)) {
						button = menuButtons.item(++index);
					}

					if (button) {
						if (keyCode >= 37 && keyCode <= 40) {
							event.halt();
						}

						var MenuManager = DockbarCustom.MenuManager;

						MenuManager.hideAll();

						button.focus();
					}
				}
			}
		};

		var Underlay = A.Component.create(
			{
				ATTRS: {
					bodyContent: {
						value: A.Node.create('<div style="height: 100px"></div>')
					},
					className: {
						lazyAdd: false,
						setter: function(value) {
							var instance = this;

							instance.get('boundingBox').addClass(value);
						},
						value: null
					}
				},

				EXTENDS: A.OverlayBase,

				NAME: 'underlay',

				prototype: {
					initializer: function() {
						var instance = this;

						DockbarCustom.UnderlayManager.register(instance);
					},

					renderUI: function() {
						var instance = this;

						Underlay.superclass.renderUI.apply(instance, arguments);

						var closeTool = new A.ButtonItem('close');

						closeTool.render(instance.get('boundingBox'));

						closeTool.get('contentBox').addClass('aui-underlay-close');

						instance.set('headerContent', closeTool.get('boundingBox'));

						instance.closeTool = closeTool;
					},

					bindUI: function() {
						var instance = this;

						Underlay.superclass.bindUI.apply(instance, arguments);

						instance.closeTool.on('click', instance.hide, instance);
					}
				}
			}
		);

		DockbarCustom.Underlay = Underlay;

		Liferay.provide(
			DockbarCustom,
			'_loadAddApplications',
			function(event, id, obj) {
				var contentBox = DockbarCustom.addApplication.get('contentBox');

				LayoutConfiguration._dialogBody = contentBox;

				LayoutConfiguration._loadContent();
			},
			['liferay-layout-configuration']
		);

		Liferay.DockbarCustom = DockbarCustom;
	},
	'',
	{
		requires: ['aui-button-item', 'aui-io-plugin', 'aui-io-request', 'aui-overlay-context', 'aui-overlay-manager', 'event-touch', 'node-focusmanager']
	}
);

// ---------------------------------------
// Layout Configuration 
// ---------------------------------------
;(function(A, Liferay) {
	var Browser = Liferay.Browser;
	var Util = Liferay.Util;

	var LayoutConfiguration = {};

	Liferay.provide(
		LayoutConfiguration,
		'showTemplates',
		function() {
			var instance = this;

			var dialog = instance._layoutDialog;

			if (!dialog) {
				var url = themeDisplay.getPathMain() + '/layout_configuration/templates';

				var dialog = new A.Dialog(
					{
						centered: true,
						modal: true,
						title: Liferay.Language.get('layout'),
						width: 700
					}
				).render();

				dialog.plug(
					A.Plugin.IO,
					{
						data: {
							doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
							p_l_id: themeDisplay.getPlid(),
							redirect: Liferay.currentURL
						},
						uri: url
					}
				);

				instance._layoutDialog = dialog;
			}

			dialog.show();
		},
		['aui-dialog']
	);

	Liferay.provide(
		LayoutConfiguration,
		'toggle',
		function(ppid) {
			var instance = this;

			var dialog = instance._applicationsDialog;

			if (!dialog) {
				var body = A.getBody();

				var url = themeDisplay.getPathMain() + '/portal/render_portlet';

				var dialog = new A.Dialog(
					{
						on: {
							visibleChange: function(event) {
								body.toggleClass('lfr-has-sidebar', event.newVal);
							}
						},
						title: Liferay.Language.get('add-application'),
						width: 250
					}
				).render();

				var contentBox = dialog.get('contentBox');

				dialog.plug(
					A.Plugin.IO,
					{
						data: {
							doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
							p_l_id: themeDisplay.getPlid(),
							p_p_id: ppid,
							p_p_state: 'exclusive'
						},
						after: {
							success: function(event) {
								instance._dialogBody = contentBox;

								instance._loadContent();
							}
						},
						uri: url
					}
				);

				instance._applicationsDialog = dialog;
			}

			dialog.show();
		},
		['aui-dialog', 'liferay-layout-configuration']
	);

	A.add(
		'liferay-layout-configuration',
		function(A) {
			var DDM = A.DD.DDM;
			var Layout = Liferay.Layout;

			A.mix(
				LayoutConfiguration,
				{
					categories: [],
					portlets: [],
					showTimer: 0,

					init: function() {
						var instance = this;

						var menu = A.one('#portal_add_content');

						instance.menu = menu;

						if (menu) {
							instance.portlets = menu.all('.lfr-portlet-item');
							instance.categories = menu.all('.lfr-content-category');
							instance.categoryContainers = menu.all('.lfr-add-content');

							var data = function(node) {
								var id = node.attr('id');

								var dataNode = A.one('#' + id + 'CategoryPath');

								var value = (dataNode && dataNode.val()) || '';

								return [Util.uncamelize(value), value].join(' ').toLowerCase();
							};

							var isVisible = function(item, index, collection) {
								return !item.hasClass('aui-helper-hidden');
							};

							new A.LiveSearch(
								{
									data: data,
									hide: function(node) {
										node.hide();
									},
									input: '#layout_configuration_content',
									nodes: '#portal_add_content .lfr-portlet-item',
									show: function(node) {
										node.show();

										var categoryParent = node.ancestorsByClassName('lfr-content-category');
										var contentParent = node.ancestorsByClassName('lfr-add-content');

										if (categoryParent) {
											categoryParent.show();
										}

										if (contentParent) {
											contentParent.replaceClass('collapsed', 'expanded');
											contentParent.show();
										}
									}
								}
							);

							new A.LiveSearch(
								{
									after: {
										search: function(event) {
											if (!this.query) {
												instance.categories.hide();
												instance.categoryContainers.replaceClass('expanded', 'collapsed');
												instance.categoryContainers.show();
											}

											if (this.query == '*') {
												instance.categories.show();
												instance.categoryContainers.replaceClass('collapsed', 'expanded');
												instance.categoryContainers.show();

												instance.portlets.show();
											}
										}
									},
									data: data,
									hide: function(node) {
										var children = node.all('.lfr-content-category > div');

										var action = 'hide';

										if (children.some(isVisible)) {
											action = 'show';
										}

										node[action]();
									},
									input: '#layout_configuration_content',
									nodes: '#portal_add_content .lfr-add-content'
								}
							);
						}
					},

					_addPortlet: function(portlet, options) {
						var instance = this;

						var portletMetaData = instance._getPortletMetaData(portlet);

						if (!portletMetaData.portletUsed) {
							var plid = portletMetaData.plid;
							var portletId = portletMetaData.portletId;
							var isInstanceable = portletMetaData.instanceable;

							if (!isInstanceable) {
								instance._disablePortletEntry(portletId);
							}

							var beforePortletLoaded = null;
							var onComplete = null;
							var placeHolder = A.Node.create('<div class="loading-animation" />');

							if (options) {
								var item = options.item;

								item.placeAfter(placeHolder);
								item.remove(true);

								beforePortletLoaded = options.beforePortletLoaded;
							}
							else {
								var layoutOptions = Layout.options;
								var firstColumn = A.one(layoutOptions.dropNodes);

								if (firstColumn) {
									var dropColumn = firstColumn.one(layoutOptions.dropContainer);
									var referencePortlet = Layout.findReferencePortlet(dropColumn);

									if (referencePortlet) {
										referencePortlet.placeBefore(placeHolder);
									}
									else {
										if (dropColumn) {
											dropColumn.append(placeHolder);
										}
									}
								}
							}

							var portletOptions = {
								beforePortletLoaded: beforePortletLoaded,
								onComplete: function(portletBoundary) {
									Layout.syncDraggableClassUI();
									Layout.updatePortletDropZones(portletBoundary);

									if (onComplete) {
										onComplete.apply(this, arguments);
									}
								},
								plid: plid,
								portletId: portletId,
								placeHolder: placeHolder
							};

							Liferay.Portlet.add(portletOptions);
						}
					},

					_disablePortletEntry: function(portletId) {
						var instance = this;

						instance._eachPortletEntry(
							portletId,
							function(item, index) {
								item.addClass('lfr-portlet-used');
							}
						);
					},

					_eachPortletEntry: function(portletId, callback) {
						var instance = this;

						var portlets = A.all('[portletid=' + portletId + ']');

						portlets.each(callback);
					},

					_enablePortletEntry: function(portletId) {
						var instance = this;

						instance._eachPortletEntry(
							portletId,
							function(item, index) {
								item.removeClass('lfr-portlet-used');
							}
						);
					},

					_getPortletMetaData: function(portlet) {
						var instance = this;

						var portletMetaData = portlet._LFR_portletMetaData;

						if (!portletMetaData) {
							var instanceable = (portlet.attr('instanceable') == 'true');
							var plid = portlet.attr('plid');
							var portletId = portlet.attr('portletId');
							var portletUsed = portlet.hasClass('lfr-portlet-used');

							portletMetaData = {
								instanceable: instanceable,
								plid: plid,
								portletId: portletId,
								portletUsed: portletUsed
							};

							portlet._LFR_portletMetaData = portletMetaData;
						}

						return portletMetaData;
					},

					_loadContent: function() {
						var instance = this;

						Liferay.fire('initLayout');

						instance.init();

						Util.addInputType();

						Liferay.on('closePortlet', instance._onPortletClose, instance);

						instance._portletItems = instance._dialogBody.all('div.lfr-portlet-item');

						var portlets = instance._portletItems;

						instance._dialogBody.delegate(
							'mousedown',
							function(event) {
								var link = event.currentTarget;
								var portlet = link.ancestor('.lfr-portlet-item');

								instance._addPortlet(portlet);
							},
							'a'
						);

						var portletItem = null;
						var layoutOptions = Layout.options;

						var portletItemOptions = {
							delegateConfig: {
								container: '#portal_add_content',
								dragConfig: {
									clickPixelThresh: 0,
									clickTimeThresh: 0
								},
								invalid: '.lfr-portlet-used',
								target: false
							},
							dragNodes: '.lfr-portlet-item',
							dropContainer: function(dropNode) {
								return dropNode.one(layoutOptions.dropContainer);
							},
							on: Layout.DEFAULT_LAYOUT_OPTIONS.on
						};

						if (layoutOptions.freeForm) {
							portletItem = new Layout.FreeFormPortletItem(portletItemOptions);
						}
						else {
							portletItem = new Layout.PortletItem(portletItemOptions);
						}

						if (Browser.isIe()) {
							instance._dialogBody.delegate(
								'mouseenter',
								function(event) {
									event.currentTarget.addClass('over');
								},
								'.lfr-portlet-item'
							);

							instance._dialogBody.delegate(
								'mouseenter',
								function(event) {
									event.currentTarget.removeClass('over');
								},
								'.lfr-portlet-item'
							);
						}

						instance._dialogBody.delegate(
							'mousedown',
							function(event) {
								var heading = event.currentTarget.get('parentNode');
								var category = heading.one('> .lfr-content-category');

								if (category) {
									category.toggle();
								}

								if (heading) {
									heading.toggleClass('collapsed').toggleClass('expanded');
								}
							},
							'.lfr-add-content > h2'
						);

						Util.focusFormField('#layout_configuration_content');
					},

					_onPortletClose: function(event) {
						var instance = this;

						var popup = A.one('#portal_add_content');

						if (popup) {
							var item = popup.one('.lfr-portlet-item[plid=' + event.plid + '][portletId=' + event.portletId + '][instanceable=false]');

							if (item && item.hasClass('lfr-portlet-used')) {
								var portletId = item.attr('portletId');

								instance._enablePortletEntry(portletId);
							}
						}
					}
				}
			);

			var PROXY_NODE_ITEM = Layout.PROXY_NODE_ITEM;

			var PortletItem = A.Component.create(
				{

					ATTRS: {
						lazyStart: {
							value: true
						},

						proxyNode: {
							value: PROXY_NODE_ITEM
						}
					},

					EXTENDS: Layout.ColumnLayout,

					NAME: 'PortletItem',

					prototype: {
						PROXY_TITLE: PROXY_NODE_ITEM.one('.portlet-title'),

						bindUI: function() {
							var instance = this;

							PortletItem.superclass.bindUI.apply(this, arguments);

							instance.on('placeholderAlign', instance._onPlaceholderAlign);
						},

						_addPortlet: function(portletNode, options) {
							var instance = this;

							LayoutConfiguration._addPortlet(portletNode, options);
						},

						_getAppendNode: function() {
							var instance = this;

							instance.appendNode = DDM.activeDrag.get('node').clone();

							return instance.appendNode;
						},

						_onDragEnd: function(event) {
							var instance = this;

							PortletItem.superclass._onDragEnd.apply(this, arguments);

							var appendNode = instance.appendNode;

							if (appendNode && appendNode.inDoc()) {
								var portletNode = event.target.get('node');

								instance._addPortlet(
									portletNode,
									{
										item: instance.appendNode
									}
								);
							}
						},

						_onDragStart: function() {
							var instance = this;

							PortletItem.superclass._onDragStart.apply(this, arguments);

							instance._syncProxyTitle();
						},

						_onPlaceholderAlign: function(event) {
							var instance = this;

							var drop = event.drop;
							var portletItem = event.currentTarget;

							if (drop && portletItem) {
								var dropNodeId = drop.get('node').get('id');

								if (Layout.EMPTY_COLUMNS[dropNodeId]) {
									portletItem.activeDrop = drop;
									portletItem.lazyEvents = false;
									portletItem.quadrant = 1;
								}
							}
						},

						_positionNode: function(event) {
							var instance = this;

							var portalLayout = event.currentTarget;
							var activeDrop = portalLayout.lastAlignDrop || portalLayout.activeDrop;

							if (activeDrop) {
								var dropNode = activeDrop.get('node');

								if (dropNode.isStatic) {
									var options = Layout.options;
									var dropColumn = dropNode.ancestor(options.dropContainer);
									var foundReferencePortlet = Layout.findReferencePortlet(dropColumn);

									if (!foundReferencePortlet) {
										foundReferencePortlet = Layout.getLastPortletNode(dropColumn);
									}

									if (foundReferencePortlet) {
										var drop = DDM.getDrop(foundReferencePortlet);

										if (drop) {
											portalLayout.quadrant = 4;
											portalLayout.activeDrop = drop;
											portalLayout.lastAlignDrop = drop;
										}
									}
								}

								PortletItem.superclass._positionNode.apply(this, arguments);
							}
						},

						_syncProxyTitle: function() {
							var instance = this;

							var node = DDM.activeDrag.get('node');
							var title = node.attr('title');

							instance.PROXY_TITLE.html(title);
						}
					}
				}
			);

			var FreeFormPortletItem = A.Component.create(
				{
					ATTRS: {
						lazyStart: {
							value: false
						}
					},

					EXTENDS: PortletItem,

					NAME: 'FreeFormPortletItem',

					prototype: {
						initializer: function() {
							var instance = this;

							var placeholder = instance.get('placeholder');

							if (placeholder) {
								placeholder.addClass(Layout.options.freeformPlaceholderClass);
							}
						}
					}
				}
			);

			Layout.FreeFormPortletItem = FreeFormPortletItem;
			Layout.PortletItem = PortletItem;
		},
		'',
		{
			requires: ['aui-live-search', 'dd', 'liferay-layout'],
			use: []
		}
	);

	Liferay.LayoutConfiguration = LayoutConfiguration;
})(AUI(), Liferay);