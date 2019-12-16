var DatePicker = {
	updateDay : function(id, hasLabel){
		this.updateDaysInMonth(id + '.day', id + '.month',id + '.year',hasLabel);
	},
	updateRangeDay : function(id, hasLabel){
		this.updateDaysInMonth(id + '.startDay', id + '.startMonth',id + '.startYear', hasLabel);
		this.updateDaysInMonth(id + '.endDay', id + '.endMonth',id + '.endYear', hasLabel);
	},
	updateDaysInMonth : function(dayId,monthId,yearId, hasLabel){
		var day = document.getElementById(dayId);
		var month = document.getElementById(monthId);
		var year = document.getElementById(yearId);
		
		var numDays = 31;
		var numOptions = 32;

		if ((hasLabel && month.selectedIndex > 0 && year.selectedIndex > 0) || !hasLabel) {
			var month = month.options[month.selectedIndex].value;
			var year = year.options[year.selectedIndex].value;
			var newDate = new Date(year, month, numOptions);
			numDays = numOptions - newDate.getDate();
		}

		numDays = (hasLabel == null || hasLabel == false) ? numDays : numDays + 1;

		var options = day.options;
		var index = (hasLabel == null || hasLabel == false) ? options.length + 1 : options.length;
		while (options.length < numDays) {
			options.add(new Option(index, index));
			index++;
		}
		if (index != numDays && numDays <= 31) {
			options.length = numDays;
		}	
	},	
	showHideSpecificPeriod : function(el) {				                
		jQuery(document.getElementById(el)).each(function(index) {
		 	if (this.value=="SPECIFIC_DATES") {
			 	jQuery(this).siblings('.dateRange').css('display', 'block');
		 	} else {
			 	jQuery(this).siblings('.dateRange').css('display', 'none');				 
		 	}
		});
	}       
}

var Desc = {	
	show: function(el) {
		jQuery('.desc').hide();
		jQuery(el).next('.desc').show();
	},
	showOnFocus: function(el) {
		var question = jQuery(el).parent().nextAll('.question');
		Desc.show(question);		
	},
	hide: function(el) {
		jQuery('.desc').hide();
	},
	hideOnBlur: function(el) {
		var question = jQuery(el).parent().nextAll('.question');
		Desc.hide(question);		
	}
};

/*
 * The panel state is stored as a 1 character string with values [0...3] which
 * represents which panel section to expand.
 */
var OperatorCookie = {
	cookie : "",

	COOKIE_NAME : "merlin-operator",

	init : function() {
		this.cookie = jQuery.cookie(this.COOKIE_NAME);

		if (this.cookie == null) {
			this.setExpanded(1);
		}
	},
	getExpanded : function() {
		return this.cookie;
	},
	setExpanded : function(index) {
		this.cookie = index;
		this.updateCookie();
	},
	updateCookie : function() {
		jQuery.cookie(this.COOKIE_NAME, this.cookie, {
			expires :360,
			path :'/'
		});
	}
};

var Panel = {		
	treeview : null,
		
	init : function() {
		OperatorCookie.init();
		
		this.treeView = new YAHOO.widget.TreeView("tree");	
        this.treeView.draw();
        
        var index = OperatorCookie.getExpanded();
        this.expand(index);
        this.treeView.subscribe("expand", function(node) {
			OperatorCookie.setExpanded(node.index);
		});
	},
	close : function() {
		jQuery('#panel .toggle a').removeClass('left').addClass('right').attr(
				'href', 'javascript:Panel.open()');
		jQuery('#panel').animate( {
			marginLeft :-175
		});
		jQuery('#wrapper').animate( {
			marginLeft :25
		});
		jQuery('#quickSearch').hide();
		jQuery('#tree div').css('visibility', 'hidden');
	},	
	expand: function(index) {
		this.treeView.getNodeByIndex(index).expand();
	},		
	open : function() {
		jQuery('#panel .toggle a').removeClass('right').addClass('left').attr(
				'href', 'javascript:Panel.close()');
		jQuery('#panel').animate( {
			marginLeft :0
		});
		jQuery('#wrapper').animate( {
			marginLeft :200
		});
		jQuery('#quickSearch').show();
		jQuery('#tree div').css('visibility', 'visible');
	}
};

var Stats = {
	closePopUp : function() {
    	var popup = (window.name == 'Statistics') ? window : window.opener;
    	popup.close();
	},	
	popOut : function() {
		window.name = document.title;
		window.popup = window
				.open(
						'/merlin/operator/reports/daily-stats/popup.xhtml',
						'Statistics',
						'titlebar=0,toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,width=500,height=293,top=100,left=100');

	},
	popInFromPopUp : function() {
		window.opener.popInDailyStats();
	},
	popInFromPanel : function() {
		var popup = window
				.open(
						'',
						'Statistics',
						'titlebar=0,toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizabsle=0,width=1,height=1,top=100,left=100');
		popup.close();
		window.popInDailyStats();
	}
};

var Top = {
	framed : function() {
		jQuery("#top").css('position', 'fixed');
		jQuery('#panel').css('position', 'fixed');
	},
	noFrames : function() {
		jQuery("#top").css('position', 'absolute');
		jQuery('#panel').css('position', 'absolute');
	}
};

function toggleComments(el) {
	var a = jQuery(el);
	var tds = el.parentNode.parentNode.childNodes;

	for ( var m in tds) {
		var tdChildren = tds[m].childNodes;
		for ( var n in tdChildren) {
			if (tdChildren[n].className == "short"
					|| tdChildren[n].className == "full") {
				jQuery(tdChildren[n]).toggle();
			}
		}
	}

	if (a.html() == "Collapse") {
		a.html("Expand");
	} else {
		a.html("Collapse");
	}
}

function toggleReport(el) {
	jQuery(document).ready( function() {
		jQuery('table .option').toggle();
	});
	var toggle = jQuery(el);
	if (toggle.hasClass('expand')) {
		toggle.removeClass('expand').addClass('collapse').html('Collapse Table');
	} else {
		toggle.removeClass('collapse').addClass('expand').html('Expand Table');
	}
}

jQuery(document).ready(function() {
	jQuery('.question').parent('.row').find('.field :input').bind('blur', function() { 
		Desc.hideOnBlur(this); 
	});	
	jQuery('.question').parent('.row').find('.field  :input').bind('focus', function() { 
		Desc.showOnFocus(this); 
	});
});