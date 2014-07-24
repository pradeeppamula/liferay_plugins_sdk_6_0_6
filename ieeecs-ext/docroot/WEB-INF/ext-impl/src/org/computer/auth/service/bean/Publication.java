/**
 * 
 */
package org.computer.auth.service.bean;

public enum Publication implements ControlledItem {
		
		COMPUTER 
			("computer", 	"Computer Magazine",						Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("8000000000000000", 16)),
		COMPUTER_GRAPHICS 
			("cga", 		"Computer Graphics &amp; Applications",		Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("4000000000000000", 16)),
		COMPUTING_SCIENCE_ENGINEERING
			("cise", 		"Computing in Science &amp; Engineering",	Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("2000000000000000", 16)),
		CONCURRENCY
			("concurrency",	"Concurrency",								Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("1000000000000000", 16)),
		DESIGN_TEST
			("dt",	 		"Design &amp; Test of Computers",			Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0800000000000000", 16)),
		DISRIBUTED_SYSTEMS_ONLINE
			("dsonline", 	"DS Online",								Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0400000000000001", 16)),
		IEEE_ANNALS_OF_COMPUTING_HISTORY
			("annals", 		"Annals of the History of Computing",		Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0200000000000000", 16)),
		INTELLIGENT_SYSTEMS
			("intelligent", "Intelligent Systems",						Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0100000000000000", 16)),
		INTERNET_COMPUTING
			("internet", 	"Internet Computing",						Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0080000000000000", 16)),
		IT_PROFESSIONAL
			("itpro", 		"IT Professional",							Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0040000000000000", 16)),
		MICRO
			("micro", 		"Micro",									Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0020000000000000", 16)),
		MULTIMEDIA
			("multimedia", 	"MultiMedia",								Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0010000000000000", 16)),
		PERVASIVE_COMPUTING
			("pc", 			"Pervasive Computing",						Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0008000000000000", 16)),
		SECURITY_PRIVACY
			("Security", 	"Security &amp; Privacy",					Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0004000000000000", 16)),
		SOFTWARE
			("software", 	"Software",									Category.MAGAZINES.getKeyCategory(), new java.math.BigInteger("0002000000000000", 16)),
			
		COMPUTER_ARCHITECTURE
			("cal", 		"Computer Architecture",					Category.LETTERS.getKeyCategory(),	 new java.math.BigInteger("8000000000000000", 16)),
				
		COMPUTATIONAL_BIOLOGY
			("tcbb",		"Computational Biology &amp; Bioinformatics",	Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("8000000000000000", 16)),
		COMPUTERS
			("tc",			"Computers",								Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("4000000000000000", 16)),
		DEPENDABLE_SECURE_COMPUTING
			("tdsc",		"Dependable &amp; Secure Computing",		Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("2000000000000000", 16)),
		MOBILE_COMPUTING
			("tmc",			"Mobile Computing",							Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("1000000000000000", 16)),
		PARALLEL_DISTRIBUTED_SYSTEMS
			("tpds",		"Parallel &amp; Distributed Systems",		Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0800000000000000", 16)),
		PATTERN_ANALYSIS
			("tpami",		"Pattern Analysis &amp; Machine Intelligence", 	Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0400000000000000", 16)),
		SOFTWARE_ENGINEERING
			("tse",			"Software Engineering",						Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0200000000000000", 16)),
		KNOWLEDGE_DATA_ENGINEERING
			("tkde",		"Knowledge Data Engineering",				Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0100000000000000", 16)),
		VISUALIZATION_COMPUTER_GRAPHICS
			("tvcg",		"Visualization &amp; Computer Graphics",	Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0080000000000000", 16)),
		LEARNING_TECHNOLOGIES
			("lt",			"Learning Technologies",					Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0040000000000000", 16)),
		SERVICES_COMPUTING
			("sc",			"Services Computing",						Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0020000000000000", 16)),
		HAPTICS
			("haptics",		"Haptics",									Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0010000000000000", 16)),
		AFFECTIVE_COMPUTING
			("tac",			"Affective Computing",						Category.TRANSACTIONS.getKeyCategory(), new java.math.BigInteger("0008000000000000", 16)),

		PROCEEDINGS
			("proceedings",	"Conference Proceedings",					Category.PROCEEDINGS.getKeyCategory(),  new java.math.BigInteger("FFFFFFFFFFFFFFF0", 16));

		private Publication(String name, String title, KeyCategory keyCategory, java.math.BigInteger keyMask) {
			this.name = name;
			this.title = title;
			this.category = keyCategory;
			this.mask = keyMask;
		}

		public KeyCategory getKeyCategory() {
			return this.category;
		}

		public java.math.BigInteger getAccessMask() {
//			_log.debug("Access Mask for " + this.name + " = " + this.mask.toString(16));
			return this.mask;
		}

		public String getName() {
			return this.name;
		}
		
		public String getTitle() {
			return this.title;
		}
		
		public Long getId() {
			return new Long(this.name.hashCode());
		}
		
		private String name = "";
		private String title = "";
		private KeyCategory category;
		private java.math.BigInteger mask = java.math.BigInteger.ZERO;
		
		private enum Category {
			MAGAZINES		("magazines",		"M"),
			PROCEEDINGS		("proceedings",		"P"),
			TRANSACTIONS	("transactions", 	"T"),
			LETTERS			("letters",			"L");
			
			private Category(String name, String code) {
				this.keyCategory = new KeyCategory(name, code);
			}
			
			public KeyCategory getKeyCategory() {
				return this.keyCategory;
			}
			
			private KeyCategory keyCategory = null;
		}
	}