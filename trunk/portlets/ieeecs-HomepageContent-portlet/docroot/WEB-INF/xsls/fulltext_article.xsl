<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">


	<xsl:output method="xhtml"
				version="1.0"
				encoding="iso-8859-1"
				use-character-maps="cm1"
				indent="yes"/>

    <xsl:character-map name="cm1">
		<xsl:output-character character="&#8212;" string='"'/>
		<xsl:output-character character="&#x2014;" string="&amp;#x2014;"/>
		<xsl:output-character character="&#x00F8;" string="&amp;#x00F8;"/>
		<xsl:output-character character="&#x00C1;" string="&amp;#x00C1;"/>
		<xsl:output-character character="&#x00E1;" string="&amp;#x00E1;"/>
		<xsl:output-character character="&#x0102;" string="&amp;#x0102;"/>
		<xsl:output-character character="&#x0103;" string="&amp;#x0103;"/>
		<xsl:output-character character="&#x00C2;" string="&amp;#x00C2;"/>
		<xsl:output-character character="&#x00E2;" string="&amp;#x00E2;"/>
		<xsl:output-character character="&#x00B4;" string="&amp;#x00B4;"/>
		<xsl:output-character character="&#x00C6;" string="&amp;#x00C6;"/>
		<xsl:output-character character="&#x00E6;" string="&amp;#x00E6;"/>
		<xsl:output-character character="&#x0391;" string="&amp;#x0391;"/>
		<xsl:output-character character="&#x03B1;" string="&amp;#x03B1;"/>
		<xsl:output-character character="&#x00C0;" string="&amp;#x00C0;"/>
		<xsl:output-character character="&#x00E0;" string="&amp;#x00E0;"/>
		<xsl:output-character character="&#x2135;" string="&amp;#x2135;"/>
		<xsl:output-character character="&#x03B1;" string="&amp;#x03B1;"/>
		<xsl:output-character character="&#x0100;" string="&amp;#x0100;"/>
		<xsl:output-character character="&#x0101;" string="&amp;#x0101;"/>
		<xsl:output-character character="&#x2227;" string="&amp;#x2227;"/>
		<xsl:output-character character="&#x221F;" string="&amp;#x221F;"/>
		<xsl:output-character character="&#x2222;" string="&amp;#x2222;"/>
		<xsl:output-character character="&#x212B;" string="&amp;#x212B;"/>
		<xsl:output-character character="&#x0104;" string="&amp;#x0104;"/>
		<xsl:output-character character="&#x0105;" string="&amp;#x0105;"/>
		<xsl:output-character character="&#x2248;" string="&amp;#x2248;"/>
		<xsl:output-character character="&#x00C5;" string="&amp;#x00C5;"/>
		<xsl:output-character character="&#x00E5;" string="&amp;#x00E5;"/>
		<xsl:output-character character="&#x2217;" string="&amp;#x2217;"/>
		<xsl:output-character character="&#x00C3;" string="&amp;#x00C3;"/>
		<xsl:output-character character="&#x00E3;" string="&amp;#x00E3;"/>
		<xsl:output-character character="&#x00C4;" string="&amp;#x00C4;"/>
		<xsl:output-character character="&#x00E4;" string="&amp;#x00E4;"/>
		<xsl:output-character character="&#x2235;" string="&amp;#x2235;"/>
		<xsl:output-character character="&#x212C;" string="&amp;#x212C;"/>
		<xsl:output-character character="&#x03B2;" string="&amp;#x03B2;"/>
		<xsl:output-character character="&#x0392;" string="&amp;#x0392;"/>
		<xsl:output-character character="&#x03B2;" string="&amp;#x03B2;"/>
		<xsl:output-character character="&#x2423;" string="&amp;#x2423;"/>
		<xsl:output-character character="&#x2592;" string="&amp;#x2592;"/>
		<xsl:output-character character="&#x2591;" string="&amp;#x2591;"/>
		<xsl:output-character character="&#x2593;" string="&amp;#x2593;"/>
		<xsl:output-character character="&#x2588;" string="&amp;#x2588;"/>
		<xsl:output-character character="&#x22A5;" string="&amp;#x22A5;"/>
		<xsl:output-character character="&#x02D8;" string="&amp;#x02D8;"/>
		<xsl:output-character character="&#x00A6;" string="&amp;#x00A6;"/>
		<xsl:output-character character="&#x2022;" string="&amp;#x2022;"/>
		<xsl:output-character character="&#x0106;" string="&amp;#x0106;"/>
		<xsl:output-character character="&#x0107;" string="&amp;#x0107;"/>
		<xsl:output-character character="&#x2229;" string="&amp;#x2229;"/>
		<xsl:output-character character="&#x2041;" string="&amp;#x2041;"/>
		<xsl:output-character character="&#x02C7;" string="&amp;#x02C7;"/>
		<xsl:output-character character="&#x010C;" string="&amp;#x010C;"/>
		<xsl:output-character character="&#x010D;" string="&amp;#x010D;"/>
		<xsl:output-character character="&#x00C7;" string="&amp;#x00C7;"/>
		<xsl:output-character character="&#x00E7;" string="&amp;#x00E7;"/>
		<xsl:output-character character="&#x0108;" string="&amp;#x0108;"/>
		<xsl:output-character character="&#x0109;" string="&amp;#x0109;"/>
		<xsl:output-character character="&#x010A;" string="&amp;#x010A;"/>
		<xsl:output-character character="&#x22C5;" string="&amp;#x22C5;"/>
		<xsl:output-character character="&#x00B8;" string="&amp;#x00B8;"/>
		<xsl:output-character character="&#x00A2;" string="&amp;#x00A2;"/>
		<xsl:output-character character="&#x2713;" string="&amp;#x2713;"/>
		<xsl:output-character character="&#x03C7;" string="&amp;#x03C7;"/>
		<xsl:output-character character="&#x25CB;" string="&amp;#x25CB;"/>
		<xsl:output-character character="&#x2218;" string="&amp;#x2218;"/>
		<xsl:output-character character="&#x2663;" string="&amp;#x2663;"/>
		<xsl:output-character character="&#x2218;" string="&amp;#x2218;"/>
		<xsl:output-character character="&#x2245;" string="&amp;#x2245;"/>
		<xsl:output-character character="&#x222E;" string="&amp;#x222E;"/>
		<xsl:output-character character="&#x00A9;" string="&amp;#x00A9;"/>
		<xsl:output-character character="&#x2117;" string="&amp;#x2117;"/>
		<xsl:output-character character="&#x2717;" string="&amp;#x2717;"/>
		<xsl:output-character character="&#x222A;" string="&amp;#x222A;"/>
		<xsl:output-character character="&#x00A4;" string="&amp;#x00A4;"/>
		<xsl:output-character character="&#x2020;" string="&amp;#x2020;"/>
		<xsl:output-character character="&#x2021;" string="&amp;#x2021;"/>
		<xsl:output-character character="&#x2193;" string="&amp;#x2193;"/>
		<xsl:output-character character="&#x2010;" string="&amp;#x2010;"/>
		<xsl:output-character character="&#x02DD;" string="&amp;#x02DD;"/>
		<xsl:output-character character="&#x010E;" string="&amp;#x010E;"/>
		<xsl:output-character character="&#x010F;" string="&amp;#x010F;"/>
		<xsl:output-character character="&#x00B0;" string="&amp;#x00B0;"/>
		<xsl:output-character character="&#x0394;" string="&amp;#x0394;"/>
		<xsl:output-character character="&#x03B4;" string="&amp;#x03B4;"/>
		<xsl:output-character character="&#x0394;" string="&amp;#x0394;"/>
		<xsl:output-character character="&#x03B4;" string="&amp;#x03B4;"/>
		<xsl:output-character character="&#x2666;" string="&amp;#x2666;"/>
		<xsl:output-character character="&#x00A8;" string="&amp;#x00A8;"/>
		<xsl:output-character character="&#x00F7;" string="&amp;#x00F7;"/>
		<xsl:output-character character="&#x230D;" string="&amp;#x230D;"/>
		<xsl:output-character character="&#x02D9;" string="&amp;#x02D9;"/>
		<xsl:output-character character="&#x0308;" string="&amp;#x0308;"/>
		<xsl:output-character character="&#x20DC;" string="&amp;#x20DC;"/>
		<xsl:output-character character="&#x230C;" string="&amp;#x230C;"/>
		<xsl:output-character character="&#x0110;" string="&amp;#x0110;"/>
		<xsl:output-character character="&#x0111;" string="&amp;#x0111;"/>
		<xsl:output-character character="&#x25BF;" string="&amp;#x25BF;"/>
		<xsl:output-character character="&#x25BE;" string="&amp;#x25BE;"/>
		<xsl:output-character character="&#x00C9;" string="&amp;#x00C9;"/>
		<xsl:output-character character="&#x00E9;" string="&amp;#x00E9;"/>
		<xsl:output-character character="&#x011A;" string="&amp;#x011A;"/>
		<xsl:output-character character="&#x011B;" string="&amp;#x011B;"/>
		<xsl:output-character character="&#x00CA;" string="&amp;#x00CA;"/>
		<xsl:output-character character="&#x00EA;" string="&amp;#x00EA;"/>
		<xsl:output-character character="&#x0116;" string="&amp;#x0116;"/>
		<xsl:output-character character="&#x0117;" string="&amp;#x0117;"/>
		<xsl:output-character character="&#x0397;" string="&amp;#x0397;"/>
		<xsl:output-character character="&#x03B7;" string="&amp;#x03B7;"/>
		<xsl:output-character character="&#x0395;" string="&amp;#x0395;"/>
		<xsl:output-character character="&#x03B5;" string="&amp;#x03B5;"/>
		<xsl:output-character character="&#x00C8;" string="&amp;#x00C8;"/>
		<xsl:output-character character="&#x00E8;" string="&amp;#x00E8;"/>
		<xsl:output-character character="&#x0112;" string="&amp;#x0112;"/>
		<xsl:output-character character="&#x0113;" string="&amp;#x0113;"/>
		<xsl:output-character character="&#x2003;" string="&amp;#x2003;"/>
		<xsl:output-character character="&#x2004;" string="&amp;#x2004;"/>
		<xsl:output-character character="&#x2005;" string="&amp;#x2005;"/>
		<xsl:output-character character="&#x014A;" string="&amp;#x014A;"/>
		<xsl:output-character character="&#x014B;" string="&amp;#x014B;"/>
		<xsl:output-character character="&#x2002;" string="&amp;#x2002;"/>
		<xsl:output-character character="&#x0118;" string="&amp;#x0118;"/>
		<xsl:output-character character="&#x0119;" string="&amp;#x0119;"/>
		<xsl:output-character character="&#x03B5;" string="&amp;#x03B5;"/>
		<xsl:output-character character="&#x01B8;" string="&amp;#x01B8;"/>
		<xsl:output-character character="&#x2107;" string="&amp;#x2107;"/>
		<xsl:output-character character="&#x2261;" string="&amp;#x2261;"/>
		<xsl:output-character character="&#x03B7;" string="&amp;#x03B7;"/>
		<xsl:output-character character="&#x00D0;" string="&amp;#x00D0;"/>
		<xsl:output-character character="&#x00F0;" string="&amp;#x00F0;"/>
		<xsl:output-character character="&#x00CB;" string="&amp;#x00CB;"/>
		<xsl:output-character character="&#x00EB;" string="&amp;#x00EB;"/>
		<xsl:output-character character="&#x0021;" string="&amp;#x0021;"/>
		<xsl:output-character character="&#x2203;" string="&amp;#x2203;"/>
		<xsl:output-character character="&#x2640;" string="&amp;#x2640;"/>
		<xsl:output-character character="&#xFB03;" string="&amp;#xFB03;"/>
		<xsl:output-character character="&#xFB00;" string="&amp;#xFB00;"/>
		<xsl:output-character character="&#xFB04;" string="&amp;#xFB04;"/>
		<xsl:output-character character="&#xFB01;" string="&amp;#xFB01;"/>
		<xsl:output-character character="&#x266D;" string="&amp;#x266D;"/>
		<xsl:output-character character="&#xFB02;" string="&amp;#xFB02;"/>
		<xsl:output-character character="&#x0192;" string="&amp;#x0192;"/>
		<xsl:output-character character="&#x2200;" string="&amp;#x2200;"/>
		<xsl:output-character character="&#x00BD;" string="&amp;#x00BD;"/>
		<xsl:output-character character="&#x2153;" string="&amp;#x2153;"/>
		<xsl:output-character character="&#x00BC;" string="&amp;#x00BC;"/>
		<xsl:output-character character="&#x2155;" string="&amp;#x2155;"/>
		<xsl:output-character character="&#x2159;" string="&amp;#x2159;"/>
		<xsl:output-character character="&#x215B;" string="&amp;#x215B;"/>
		<xsl:output-character character="&#x2154;" string="&amp;#x2154;"/>
		<xsl:output-character character="&#x2156;" string="&amp;#x2156;"/>
		<xsl:output-character character="&#x00BE;" string="&amp;#x00BE;"/>
		<xsl:output-character character="&#x2157;" string="&amp;#x2157;"/>
		<xsl:output-character character="&#x215C;" string="&amp;#x215C;"/>
		<xsl:output-character character="&#x2158;" string="&amp;#x2158;"/>
		<xsl:output-character character="&#x215A;" string="&amp;#x215A;"/>
		<xsl:output-character character="&#x215D;" string="&amp;#x215D;"/>
		<xsl:output-character character="&#x215E;" string="&amp;#x215E;"/>
		<xsl:output-character character="&#x01F5;" string="&amp;#x01F5;"/>
		<xsl:output-character character="&#x0393;" string="&amp;#x0393;"/>
		<xsl:output-character character="&#x03B3;" string="&amp;#x03B3;"/>
		<xsl:output-character character="&#x03DC;" string="&amp;#x03DC;"/>
		<xsl:output-character character="&#x011E;" string="&amp;#x011E;"/>
		<xsl:output-character character="&#x011F;" string="&amp;#x011F;"/>
		<xsl:output-character character="&#x0122;" string="&amp;#x0122;"/>
		<xsl:output-character character="&#x011C;" string="&amp;#x011C;"/>
		<xsl:output-character character="&#x011D;" string="&amp;#x011D;"/>
		<xsl:output-character character="&#x0120;" string="&amp;#x0120;"/>
		<xsl:output-character character="&#x0121;" string="&amp;#x0121;"/>
		<xsl:output-character character="&#x2265;" string="&amp;#x2265;"/>
		<xsl:output-character character="&#x0393;" string="&amp;#x0393;"/>
		<xsl:output-character character="&#x03B3;" string="&amp;#x03B3;"/>
		<xsl:output-character character="&#x0060;" string="&amp;#x0060;"/>
		<xsl:output-character character="&#x003E;" string="&amp;#x003E;"/>
		<xsl:output-character character="&#x200A;" string="&amp;#x200A;"/>
		<xsl:output-character character="&#x00BD;" string="&amp;#x00BD;"/>
		<xsl:output-character character="&#x210B;" string="&amp;#x210B;"/>
		<xsl:output-character character="&#x0124;" string="&amp;#x0124;"/>
		<xsl:output-character character="&#x0125;" string="&amp;#x0125;"/>
		<xsl:output-character character="&#x2665;" string="&amp;#x2665;"/>
		<xsl:output-character character="&#x2026;" string="&amp;#x2026;"/>
		<xsl:output-character character="&#x2015;" string="&amp;#x2015;"/>
		<xsl:output-character character="&#x0126;" string="&amp;#x0126;"/>
		<xsl:output-character character="&#x0127;" string="&amp;#x0127;"/>
		<xsl:output-character character="&#x2043;" string="&amp;#x2043;"/>
		<xsl:output-character character="&#xE4F8;" string="&amp;#xE4F8;"/>
		<xsl:output-character character="&#x00CD;" string="&amp;#x00CD;"/>
		<xsl:output-character character="&#x00ED;" string="&amp;#x00ED;"/>
		<xsl:output-character character="&#x00CE;" string="&amp;#x00CE;"/>
		<xsl:output-character character="&#x00EE;" string="&amp;#x00EE;"/>
		<xsl:output-character character="&#x0130;" string="&amp;#x0130;"/>
		<xsl:output-character character="&#x00A1;" string="&amp;#x00A1;"/>
		<xsl:output-character character="&#xE365;" string="&amp;#xE365;"/>
		<xsl:output-character character="&#x0399;" string="&amp;#x0399;"/>
		<xsl:output-character character="&#x03B9;" string="&amp;#x03B9;"/>
		<xsl:output-character character="&#x00CC;" string="&amp;#x00CC;"/>
		<xsl:output-character character="&#x00EC;" string="&amp;#x00EC;"/>
		<xsl:output-character character="&#x0132;" string="&amp;#x0132;"/>
		<xsl:output-character character="&#x0133;" string="&amp;#x0133;"/>
		<xsl:output-character character="&#x012A;" string="&amp;#x012A;"/>
		<xsl:output-character character="&#x012B;" string="&amp;#x012B;"/>
		<xsl:output-character character="&#x2105;" string="&amp;#x2105;"/>
		<xsl:output-character character="&#x221E;" string="&amp;#x221E;"/>
		<xsl:output-character character="&#x0131;" string="&amp;#x0131;"/>
		<xsl:output-character character="&#x222B;" string="&amp;#x222B;"/>
		<xsl:output-character character="&#x012E;" string="&amp;#x012E;"/>
		<xsl:output-character character="&#x012F;" string="&amp;#x012F;"/>
		<xsl:output-character character="&#x03B9;" string="&amp;#x03B9;"/>
		<xsl:output-character character="&#x00BF;" string="&amp;#x00BF;"/>
		<xsl:output-character character="&#x220A;" string="&amp;#x220A;"/>
		<xsl:output-character character="&#x0128;" string="&amp;#x0128;"/>
		<xsl:output-character character="&#x0129;" string="&amp;#x0129;"/>
		<xsl:output-character character="&#x00CF;" string="&amp;#x00CF;"/>
		<xsl:output-character character="&#x00EF;" string="&amp;#x00EF;"/>
		<xsl:output-character character="&#x0134;" string="&amp;#x0134;"/>
		<xsl:output-character character="&#x0135;" string="&amp;#x0135;"/>
		<xsl:output-character character="&#x03BA;" string="&amp;#x03BA;"/>
		<xsl:output-character character="&#x03F0;" string="&amp;#x03F0;"/>
		<xsl:output-character character="&#x0136;" string="&amp;#x0136;"/>
		<xsl:output-character character="&#x0137;" string="&amp;#x0137;"/>
		<xsl:output-character character="&#x039A;" string="&amp;#x039A;"/>
		<xsl:output-character character="&#x03BA;" string="&amp;#x03BA;"/>
		<xsl:output-character character="&#x0138;" string="&amp;#x0138;"/>
		<xsl:output-character character="&#x03A7;" string="&amp;#x03A7;"/>
		<xsl:output-character character="&#x03C7;" string="&amp;#x03C7;"/>
		<xsl:output-character character="&#x0139;" string="&amp;#x0139;"/>
		<xsl:output-character character="&#x013A;" string="&amp;#x013A;"/>
		<xsl:output-character character="&#x2112;" string="&amp;#x2112;"/>
		<xsl:output-character character="&#x039B;" string="&amp;#x039B;"/>
		<xsl:output-character character="&#x03BB;" string="&amp;#x03BB;"/>
		<xsl:output-character character="&#x3008;" string="&amp;#x3008;"/>
		<xsl:output-character character="&#x00AB;" string="&amp;#x00AB;"/>
		<xsl:output-character character="&#x2190;" string="&amp;#x2190;"/>
		<xsl:output-character character="&#x21D0;" string="&amp;#x21D0;"/>
		<xsl:output-character character="&#x013D;" string="&amp;#x013D;"/>
		<xsl:output-character character="&#x013E;" string="&amp;#x013E;"/>
		<xsl:output-character character="&#x013B;" string="&amp;#x013B;"/>
		<xsl:output-character character="&#x013C;" string="&amp;#x013C;"/>
		<xsl:output-character character="&#x201C;" string="&amp;#x201C;"/>
		<xsl:output-character character="&#x201E;" string="&amp;#x201E;"/>
		<xsl:output-character character="&#x2264;" string="&amp;#x2264;"/>
		<xsl:output-character character="&#x039B;" string="&amp;#x039B;"/>
		<xsl:output-character character="&#x03BB;" string="&amp;#x03BB;"/>
		<xsl:output-character character="&#x2584;" string="&amp;#x2584;"/>
		<xsl:output-character character="&#x013F;" string="&amp;#x013F;"/>
		<xsl:output-character character="&#x0140;" string="&amp;#x0140;"/>
		<xsl:output-character character="&#x2217;" string="&amp;#x2217;"/>
		<xsl:output-character character="&#x25CA;" string="&amp;#x25CA;"/>
		<xsl:output-character character="&#x2726;" string="&amp;#x2726;"/>
		<xsl:output-character character="&#x2018;" string="&amp;#x2018;"/>
		<xsl:output-character character="&#x201A;" string="&amp;#x201A;"/>
		<xsl:output-character character="&#x0141;" string="&amp;#x0141;"/>
		<xsl:output-character character="&#x0142;" string="&amp;#x0142;"/>
		<xsl:output-character character="&#x25C3;" string="&amp;#x25C3;"/>
		<xsl:output-character character="&#x25C2;" string="&amp;#x25C2;"/>
		<xsl:output-character character="&#x00AF;" string="&amp;#x00AF;"/>
		<xsl:output-character character="&#x2642;" string="&amp;#x2642;"/>
		<xsl:output-character character="&#x2720;" string="&amp;#x2720;"/>
		<xsl:output-character character="&#x25AE;" string="&amp;#x25AE;"/>
		<xsl:output-character character="&#x039C;" string="&amp;#x039C;"/>
		<xsl:output-character character="&#x03BC;" string="&amp;#x03BC;"/>
		<xsl:output-character character="&#x00B5;" string="&amp;#x00B5;"/>
		<xsl:output-character character="&#x00B7;" string="&amp;#x00B7;"/>
		<xsl:output-character character="&#x2212;" string="&amp;#x2212;"/>
		<xsl:output-character character="&#x2026;" string="&amp;#x2026;"/>
		<xsl:output-character character="&#x2213;" string="&amp;#x2213;"/>
		<xsl:output-character character="&#x03BC;" string="&amp;#x03BC;"/>
		<xsl:output-character character="&#x2207;" string="&amp;#x2207;"/>
		<xsl:output-character character="&#x0143;" string="&amp;#x0143;"/>
		<xsl:output-character character="&#x0144;" string="&amp;#x0144;"/>
		<xsl:output-character character="&#x0149;" string="&amp;#x0149;"/>
		<xsl:output-character character="&#x266E;" string="&amp;#x266E;"/>
		<xsl:output-character character="&#x00A0;" string="&amp;#x00A0;"/>
		<xsl:output-character character="&#x0147;" string="&amp;#x0147;"/>
		<xsl:output-character character="&#x0148;" string="&amp;#x0148;"/>
		<xsl:output-character character="&#x0145;" string="&amp;#x0145;"/>
		<xsl:output-character character="&#x0146;" string="&amp;#x0146;"/>
		<xsl:output-character character="&#x2013;" string="&amp;#x2013;"/>
		<xsl:output-character character="&#x2260;" string="&amp;#x2260;"/>
		<xsl:output-character character="&#x039D;" string="&amp;#x039D;"/>
		<xsl:output-character character="&#x03BD;" string="&amp;#x03BD;"/>
		<xsl:output-character character="&#x220D;" string="&amp;#x220D;"/>
		<xsl:output-character character="&#x2025;" string="&amp;#x2025;"/>
		<xsl:output-character character="&#x00AC;" string="&amp;#x00AC;"/>
		<xsl:output-character character="&#x2209;" string="&amp;#x2209;"/>
		<xsl:output-character character="&#x00D1;" string="&amp;#x00D1;"/>
		<xsl:output-character character="&#x00F1;" string="&amp;#x00F1;"/>
		<xsl:output-character character="&#x03BD;" string="&amp;#x03BD;"/>
		<xsl:output-character character="&#x0023;" string="&amp;#x0023;"/>
		<xsl:output-character character="&#x2007;" string="&amp;#x2007;"/>
		<xsl:output-character character="&#x00D3;" string="&amp;#x00D3;"/>
		<xsl:output-character character="&#x00F3;" string="&amp;#x00F3;"/>
		<xsl:output-character character="&#x00D4;" string="&amp;#x00D4;"/>
		<xsl:output-character character="&#x00F4;" string="&amp;#x00F4;"/>
		<xsl:output-character character="&#x0150;" string="&amp;#x0150;"/>
		<xsl:output-character character="&#x0151;" string="&amp;#x0151;"/>
		<xsl:output-character character="&#x0152;" string="&amp;#x0152;"/>
		<xsl:output-character character="&#x0153;" string="&amp;#x0153;"/>
		<xsl:output-character character="&#x02DB;" string="&amp;#x02DB;"/>
		<xsl:output-character character="&#x039F;" string="&amp;#x039F;"/>
		<xsl:output-character character="&#x03BF;" string="&amp;#x03BF;"/>
		<xsl:output-character character="&#x00D2;" string="&amp;#x00D2;"/>
		<xsl:output-character character="&#x00F2;" string="&amp;#x00F2;"/>
		<xsl:output-character character="&#x03A9;" string="&amp;#x03A9;"/>
		<xsl:output-character character="&#x03C9;" string="&amp;#x03C9;"/>
		<xsl:output-character character="&#x2126;" string="&amp;#x2126;"/>
		<xsl:output-character character="&#x014C;" string="&amp;#x014C;"/>
		<xsl:output-character character="&#x014D;" string="&amp;#x014D;"/>
		<xsl:output-character character="&#x03A9;" string="&amp;#x03A9;"/>
		<xsl:output-character character="&#x03C9;" string="&amp;#x03C9;"/>
		<xsl:output-character character="&#x2228;" string="&amp;#x2228;"/>
		<xsl:output-character character="&#x2134;" string="&amp;#x2134;"/>
		<xsl:output-character character="&#x00AA;" string="&amp;#x00AA;"/>
		<xsl:output-character character="&#x00BA;" string="&amp;#x00BA;"/>
		<xsl:output-character character="&#x00D8;" string="&amp;#x00D8;"/>
		<xsl:output-character character="&#x2298;" string="&amp;#x2298;"/>
		<xsl:output-character character="&#x00D5;" string="&amp;#x00D5;"/>
		<xsl:output-character character="&#x00F5;" string="&amp;#x00F5;"/>
		<xsl:output-character character="&#x00D6;" string="&amp;#x00D6;"/>
		<xsl:output-character character="&#x00F6;" string="&amp;#x00F6;"/>
		<xsl:output-character character="&#x2225;" string="&amp;#x2225;"/>
		<xsl:output-character character="&#x00B6;" string="&amp;#x00B6;"/>
		<xsl:output-character character="&#x2202;" string="&amp;#x2202;"/>
		<xsl:output-character character="&#x2030;" string="&amp;#x2030;"/>
		<xsl:output-character character="&#x22A5;" string="&amp;#x22A5;"/>
		<xsl:output-character character="&#x03A0;" string="&amp;#x03A0;"/>
		<xsl:output-character character="&#x03C0;" string="&amp;#x03C0;"/>
		<xsl:output-character character="&#x03A6;" string="&amp;#x03A6;"/>
		<xsl:output-character character="&#x03C6;" string="&amp;#x03C6;"/>
		<xsl:output-character character="&#x03A6;" string="&amp;#x03A6;"/>
		<xsl:output-character character="&#x03C6;" string="&amp;#x03C6;"/>
		<xsl:output-character character="&#x03D5;" string="&amp;#x03D5;"/>
		<xsl:output-character character="&#x2133;" string="&amp;#x2133;"/>
		<xsl:output-character character="&#x260E;" string="&amp;#x260E;"/>
		<xsl:output-character character="&#x03A0;" string="&amp;#x03A0;"/>
		<xsl:output-character character="&#x03C0;" string="&amp;#x03C0;"/>
		<xsl:output-character character="&#x03D6;" string="&amp;#x03D6;"/>
		<xsl:output-character character="&#x00B1;" string="&amp;#x00B1;"/>
		<xsl:output-character character="&#x00A3;" string="&amp;#x00A3;"/>
		<xsl:output-character character="&#x2032;" string="&amp;#x2032;"/>
		<xsl:output-character character="&#x2033;" string="&amp;#x2033;"/>
		<xsl:output-character character="&#x221D;" string="&amp;#x221D;"/>
		<xsl:output-character character="&#x03A8;" string="&amp;#x03A8;"/>
		<xsl:output-character character="&#x03C8;" string="&amp;#x03C8;"/>
		<xsl:output-character character="&#x03A8;" string="&amp;#x03A8;"/>
		<xsl:output-character character="&#x03C8;" string="&amp;#x03C8;"/>
		<xsl:output-character character="&#x2008;" string="&amp;#x2008;"/>
		<xsl:output-character character="&#x0154;" string="&amp;#x0154;"/>
		<xsl:output-character character="&#x0155;" string="&amp;#x0155;"/>
		<xsl:output-character character="&#x221A;" string="&amp;#x221A;"/>
		<xsl:output-character character="&#x3009;" string="&amp;#x3009;"/>
		<xsl:output-character character="&#x00BB;" string="&amp;#x00BB;"/>
		<xsl:output-character character="&#x2192;" string="&amp;#x2192;"/>
		<xsl:output-character character="&#x21D2;" string="&amp;#x21D2;"/>
		<xsl:output-character character="&#x0158;" string="&amp;#x0158;"/>
		<xsl:output-character character="&#x0159;" string="&amp;#x0159;"/>
		<xsl:output-character character="&#x0156;" string="&amp;#x0156;"/>
		<xsl:output-character character="&#x0157;" string="&amp;#x0157;"/>
		<xsl:output-character character="&#x201D;" string="&amp;#x201D;"/>
		<xsl:output-character character="&#x201C;" string="&amp;#x201C;"/>
		<xsl:output-character character="&#x25AD;" string="&amp;#x25AD;"/>
		<xsl:output-character character="&#x00AF;" string="&amp;#x00AF;"/>
		<xsl:output-character character="&#x03A1;" string="&amp;#x03A1;"/>
		<xsl:output-character character="&#x03C1;" string="&amp;#x03C1;"/>
		<xsl:output-character character="&#x03c1;" string="&amp;#x03c1;"/>
		<xsl:output-character character="&#x03F1;" string="&amp;#x03F1;"/>
		<xsl:output-character character="&#x02DA;" string="&amp;#x02DA;"/>
		<xsl:output-character character="&#x2019;" string="&amp;#x2019;"/>
		<xsl:output-character character="&#x2018;" string="&amp;#x2018;"/>
		<xsl:output-character character="&#x25B9;" string="&amp;#x25B9;"/>
		<xsl:output-character character="&#x25B8;" string="&amp;#x25B8;"/>
		<xsl:output-character character="&#x211E;" string="&amp;#x211E;"/>
		<xsl:output-character character="&#x015A;" string="&amp;#x015A;"/>
		<xsl:output-character character="&#x015B;" string="&amp;#x015B;"/>
		<xsl:output-character character="&#x0160;" string="&amp;#x0160;"/>
		<xsl:output-character character="&#x0161;" string="&amp;#x0161;"/>
		<xsl:output-character character="&#x015E;" string="&amp;#x015E;"/>
		<xsl:output-character character="&#x015F;" string="&amp;#x015F;"/>
		<xsl:output-character character="&#x015C;" string="&amp;#x015C;"/>
		<xsl:output-character character="&#x015D;" string="&amp;#x015D;"/>
		<xsl:output-character character="&#x00A7;" string="&amp;#x00A7;"/>
		<xsl:output-character character="&#x2736;" string="&amp;#x2736;"/>
		<xsl:output-character character="&#x03C2;" string="&amp;#x03C2;"/>
		<xsl:output-character character="&#x03A3;" string="&amp;#x03A3;"/>
		<xsl:output-character character="&#x03C3;" string="&amp;#x03C3;"/>
		<xsl:output-character character="&#x266F;" string="&amp;#x266F;"/>
		<xsl:output-character character="&#x00AD;" string="&amp;#x00AD;"/>
		<xsl:output-character character="&#x03A3;" string="&amp;#x03A3;"/>
		<xsl:output-character character="&#x03C3;" string="&amp;#x03C3;"/>
		<xsl:output-character character="&#x03C2;" string="&amp;#x03C2;"/>
		<xsl:output-character character="&#x223C;" string="&amp;#x223C;"/>
		<xsl:output-character character="&#x2243;" string="&amp;#x2243;"/>
		<xsl:output-character character="&#x2660;" string="&amp;#x2660;"/>
		<xsl:output-character character="&#x25A1;" string="&amp;#x25A1;"/>
		<xsl:output-character character="&#x25A1;" string="&amp;#x25A1;"/>
		<xsl:output-character character="&#x25AA;" string="&amp;#x25AA;"/>
		<xsl:output-character character="&#x22C6;" string="&amp;#x22C6;"/>
		<xsl:output-character character="&#x2605;" string="&amp;#x2605;"/>
		<xsl:output-character character="&#x2282;" string="&amp;#x2282;"/>
		<xsl:output-character character="&#x2286;" string="&amp;#x2286;"/>
		<xsl:output-character character="&#x2669;" string="&amp;#x2669;"/>
		<xsl:output-character character="&#x2283;" string="&amp;#x2283;"/>
		<xsl:output-character character="&#x00B9;" string="&amp;#x00B9;"/>
		<xsl:output-character character="&#x00B2;" string="&amp;#x00B2;"/>
		<xsl:output-character character="&#x00B3;" string="&amp;#x00B3;"/>
		<xsl:output-character character="&#x2287;" string="&amp;#x2287;"/>
		<xsl:output-character character="&#x00DF;" string="&amp;#x00DF;"/>
		<xsl:output-character character="&#x2316;" string="&amp;#x2316;"/>
		<xsl:output-character character="&#x03C4;" string="&amp;#x03C4;"/>
		<xsl:output-character character="&#x0164;" string="&amp;#x0164;"/>
		<xsl:output-character character="&#x0165;" string="&amp;#x0165;"/>
		<xsl:output-character character="&#x0162;" string="&amp;#x0162;"/>
		<xsl:output-character character="&#x0163;" string="&amp;#x0163;"/>
		<xsl:output-character character="&#x20DB;" string="&amp;#x20DB;"/>
		<xsl:output-character character="&#x2315;" string="&amp;#x2315;"/>
		<xsl:output-character character="&#x03A4;" string="&amp;#x03A4;"/>
		<xsl:output-character character="&#x03C4;" string="&amp;#x03C4;"/>
		<xsl:output-character character="&#x2234;" string="&amp;#x2234;"/>
		<xsl:output-character character="&#x0398;" string="&amp;#x0398;"/>
		<xsl:output-character character="&#x03B8;" string="&amp;#x03B8;"/>
		<xsl:output-character character="&#x03D1;" string="&amp;#x03D1;"/>
		<xsl:output-character character="&#x0398;" string="&amp;#x0398;"/>
		<xsl:output-character character="&#x03B8;" string="&amp;#x03B8;"/>
		<xsl:output-character character="&#x2009;" string="&amp;#x2009;"/>
		<xsl:output-character character="&#x00DE;" string="&amp;#x00DE;"/>
		<xsl:output-character character="&#x00FE;" string="&amp;#x00FE;"/>
		<xsl:output-character character="&#x00D7;" string="&amp;#x00D7;"/>
		<xsl:output-character character="&#x2034;" string="&amp;#x2034;"/>
		<xsl:output-character character="&#x2122;" string="&amp;#x2122;"/>
		<xsl:output-character character="&#x0166;" string="&amp;#x0166;"/>
		<xsl:output-character character="&#x0167;" string="&amp;#x0167;"/>
		<xsl:output-character character="&#x00DA;" string="&amp;#x00DA;"/>
		<xsl:output-character character="&#x00FA;" string="&amp;#x00FA;"/>
		<xsl:output-character character="&#x2191;" string="&amp;#x2191;"/>
		<xsl:output-character character="&#x016C;" string="&amp;#x016C;"/>
		<xsl:output-character character="&#x016D;" string="&amp;#x016D;"/>
		<xsl:output-character character="&#x00DB;" string="&amp;#x00DB;"/>
		<xsl:output-character character="&#x00FB;" string="&amp;#x00FB;"/>
		<xsl:output-character character="&#x0170;" string="&amp;#x0170;"/>
		<xsl:output-character character="&#x0171;" string="&amp;#x0171;"/>
		<xsl:output-character character="&#x03A5;" string="&amp;#x03A5;"/>
		<xsl:output-character character="&#x03C5;" string="&amp;#x03C5;"/>
		<xsl:output-character character="&#x00D9;" string="&amp;#x00D9;"/>
		<xsl:output-character character="&#x00F9;" string="&amp;#x00F9;"/>
		<xsl:output-character character="&#x2580;" string="&amp;#x2580;"/>
		<xsl:output-character character="&#x230F;" string="&amp;#x230F;"/>
		<xsl:output-character character="&#x016A;" string="&amp;#x016A;"/>
		<xsl:output-character character="&#x016B;" string="&amp;#x016B;"/>
		<xsl:output-character character="&#x00A8;" string="&amp;#x00A8;"/>
		<xsl:output-character character="&#x0172;" string="&amp;#x0172;"/>
		<xsl:output-character character="&#x0173;" string="&amp;#x0173;"/>
		<xsl:output-character character="&#x03A5;" string="&amp;#x03A5;"/>
		<xsl:output-character character="&#x03C5;" string="&amp;#x03C5;"/>
		<xsl:output-character character="&#x230E;" string="&amp;#x230E;"/>
		<xsl:output-character character="&#x016E;" string="&amp;#x016E;"/>
		<xsl:output-character character="&#x016F;" string="&amp;#x016F;"/>
		<xsl:output-character character="&#x0168;" string="&amp;#x0168;"/>
		<xsl:output-character character="&#x0169;" string="&amp;#x0169;"/>
		<xsl:output-character character="&#x25B5;" string="&amp;#x25B5;"/>
		<xsl:output-character character="&#x25B4;" string="&amp;#x25B4;"/>
		<xsl:output-character character="&#x00DC;" string="&amp;#x00DC;"/>
		<xsl:output-character character="&#x00FC;" string="&amp;#x00FC;"/>
		<xsl:output-character character="&#x22EE;" string="&amp;#x22EE;"/>
		<xsl:output-character character="&#x2016;" string="&amp;#x2016;"/>
		<xsl:output-character character="&#x0174;" string="&amp;#x0174;"/>
		<xsl:output-character character="&#x0175;" string="&amp;#x0175;"/>
		<xsl:output-character character="&#x2259;" string="&amp;#x2259;"/>
		<xsl:output-character character="&#x039E;" string="&amp;#x039E;"/>
		<xsl:output-character character="&#x03BE;" string="&amp;#x03BE;"/>
		<xsl:output-character character="&#x039E;" string="&amp;#x039E;"/>
		<xsl:output-character character="&#x03BE;" string="&amp;#x03BE;"/>
		<xsl:output-character character="&#x00DD;" string="&amp;#x00DD;"/>
		<xsl:output-character character="&#x00FD;" string="&amp;#x00FD;"/>
		<xsl:output-character character="&#x0176;" string="&amp;#x0176;"/>
		<xsl:output-character character="&#x0177;" string="&amp;#x0177;"/>
		<xsl:output-character character="&#x00A5;" string="&amp;#x00A5;"/>
		<xsl:output-character character="&#x00FF;" string="&amp;#x00FF;"/>
		<xsl:output-character character="&#x0178;" string="&amp;#x0178;"/>
		<xsl:output-character character="&#x0179;" string="&amp;#x0179;"/>
		<xsl:output-character character="&#x017A;" string="&amp;#x017A;"/>
		<xsl:output-character character="&#x017D;" string="&amp;#x017D;"/>
		<xsl:output-character character="&#x017E;" string="&amp;#x017E;"/>
		<xsl:output-character character="&#x017B;" string="&amp;#x017B;"/>
		<xsl:output-character character="&#x017C;" string="&amp;#x017C;"/>
		<xsl:output-character character="&#x03B6;" string="&amp;#x03B6;"/>
		<xsl:output-character character="&#x0396;" string="&amp;#x0396;"/>
		<xsl:output-character character="&#x03B6;" string="&amp;#x03B6;"/>
	</xsl:character-map>

	<!-- xsl:include href="file://filer1-ca/ieeecs/scripts/table.xsl"/ -->
	<!-- xsl:include href="table.xsl"/ -->
	<xsl:strip-space elements="*"/>
	<xsl:preserve-space elements="algorithm div lit pre st rom tf"/>

	<!-- external parameters -->
	<xsl:param name="PubType" select="''"/>
	<xsl:param name="PubAcro" select="''"/>
	<xsl:param name="PubYear" select="''"/>
	<xsl:param name="PubIss" select="''"/>
	<xsl:param name="StartPg" select="''"/>
	<xsl:param name="ShowPDF" select="''"/>
	<xsl:param name="PDF" select="''"/>
	<xsl:param name="DOI" select="''"/>

	<!-- used for case conversion -->
	<xsl:variable name="lowercase" select="'abcdefghijklmnopqrstuvwxyz'"/>
	<xsl:variable name="UPPERCASE" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>

	<!-- other top level variables -->
	<xsl:variable name="fnoString" select="translate(string(//article/fno),$UPPERCASE,$lowercase)"/>
	<xsl:variable name="fnoLength" select="string-length($fnoString)"/>

	<xsl:variable name="pubtype">
		<xsl:choose>
			<xsl:when test="$PubType = 'm'">
				<xsl:value-of select="'mags'"/>
			</xsl:when>
			<xsl:when test="$PubType = 't'">
				<xsl:value-of select="'trans'"/>
			</xsl:when>
			<xsl:when test="$PubType = 'l'">
				<xsl:value-of select="'letters'"/>
			</xsl:when>
		</xsl:choose>
	</xsl:variable>

	<xsl:variable name="pubtypename">
		<xsl:choose>
			<xsl:when test="$PubType = 'm'">
				<xsl:value-of select="'magazines'"/>
			</xsl:when>
			<xsl:when test="$PubType = 't'">
				<xsl:value-of select="'transactions'"/>
			</xsl:when>
			<xsl:when test="$PubType = 'l'">
				<xsl:value-of select="'letters'"/>
			</xsl:when>
		</xsl:choose>
	</xsl:variable>


	<xsl:variable name="issn">
		<xsl:value-of select="//article/fm/hdr/hdr1/crt/issn"/>
	</xsl:variable>

	<xsl:variable name="logo">
		<xsl:choose>
			<xsl:when test="$PubAcro = 'co'">
				<xsl:value-of select="'computer'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'an'">
				<xsl:value-of select="'annals'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'ca'">
				<xsl:value-of select="'cal'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'cs'">
				<xsl:value-of select="'cise'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'cg'">
				<xsl:value-of select="'cga'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'pd'">
				<xsl:value-of select="'concurrency'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'dt'">
				<xsl:value-of select="'dt'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'ds'">
				<xsl:value-of select="'dsodl'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'ex'">
				<xsl:value-of select="'intelligent'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'ic'">
				<xsl:value-of select="'internet'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'mi'">
				<xsl:value-of select="'micro'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'mu'">
				<xsl:value-of select="'multimedia'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'pc'">
				<xsl:value-of select="'pervasive'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'sp'">
				<xsl:value-of select="'security'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'so'">
				<xsl:value-of select="'software'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'sc'">
				<xsl:value-of select="'tsc'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'it'">
				<xsl:value-of select="'itpro'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'lt'">
				<xsl:value-of select="'tlt'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tc'">
				<xsl:value-of select="'tc'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'td'">
				<xsl:value-of select="'tpds'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'th'">
				<xsl:value-of select="'toh'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tk'">
				<xsl:value-of select="'tkde'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tm'">
				<xsl:value-of select="'tmc'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tq'">
				<xsl:value-of select="'tdsc'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tp'">
				<xsl:value-of select="'tpami'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'ts'">
				<xsl:value-of select="'tse'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tg'">
				<xsl:value-of select="'tvcg'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'tb'">
				<xsl:value-of select="'tcbb'"/>
			</xsl:when>
			<xsl:when test="$PubAcro = 'ta'">
				<xsl:value-of select="'tac'"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="'dl-logo-unknown'"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>


	<xsl:variable name="bbIdLength">
		<xsl:value-of select="string-length((//bb)[1]/@id)"/>
	</xsl:variable>
	<!-- ************************************************************* -->
	<xsl:template match="/">
		<xsl:apply-templates select="descendant::article"/>
	</xsl:template>

	<xsl:template match="article">
		<div id="maincontent">
<!--
			<div id="html-articleinfo" class="html-articleinfo">
				<table cellspacing="0">
					<xsl:for-each select="bdy/sec/st | bdy/sec/h1a | bdy/ack/h1a | bm/bib/bibl/h | bm/ack/ti | bdy/sec/bib/bibl/h">
						<xsl:if test=".!=''">
							<tr>
								<td class="content-center">
									<a>
										<xsl:choose>
											<xsl:when test="name(self::node())='ti'">
												<xsl:attribute name="href"><xsl:value-of select="'#Ack'"/></xsl:attribute>
											</xsl:when>
											<xsl:otherwise>
												<xsl:attribute name="href">
													<xsl:choose>
														<xsl:when test="name(self::node())='h'">
															<xsl:value-of select="concat('#Ref', string(count(preceding::bibl) + 1))"/>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="concat('#target', string(position()))"/>
														</xsl:otherwise>
													</xsl:choose>
												</xsl:attribute>
											</xsl:otherwise>
										</xsl:choose>
										<xsl:choose>
											<xsl:when test="@cased">
												<xsl:value-of select="@cased"/>
											</xsl:when>
											<xsl:otherwise>
												<xsl:apply-templates/>
											</xsl:otherwise>
										</xsl:choose>
									</a>
								</td>
							</tr>
						</xsl:if>
					</xsl:for-each>
				</table>
			</div>
-->
			<xsl:apply-templates select="bdy/*"/>
			<xsl:apply-templates select="bm/*"/>
			<div id="mathPopup">
				<table>
					<tr>
						<td class="popup-top-left-tex">&#x00A0;</td>
						<td class="popup-top-center-tex">
							<div id="mathPopupTitle">&#x00A0;</div>
						</td>
						<td class="popup-top-close-tex" onclick="javascript:hideText('mathPopup');" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';">x</td>
						<td class="popup-top-right-tex"/>
					</tr>
					<tr>
						<td class="popup-content-left-tex">&#x00A0;</td>
						<td class="popup-content-center-tex" colspan="2">
							<div id="mathPopupContent"/>
						</td>
						<td class="popup-content-right-tex">&#x00A0;</td>
					</tr>
					<tr>
						<td class="popup-bottom-left-tex"/>
						<td class="popup-bottom-center-tex"/>
						<td class="popup-bottom-empty-cell-tex"/>
						<td class="popup-bottom-right-tex"/>
					</tr>
				</table>
			</div>
		</div>
	</xsl:template>

	<!-- ****************************************************************
	 FRONT MATTER ELEMENTS
	***************************************************************** -->
	<xsl:template match="cci"/>
	<xsl:template match="onm">
		<xsl:choose>
			<xsl:when test="parent::cci">
				<xsl:apply-templates/>
			</xsl:when>
			<xsl:otherwise>
				<span class="authorinfo">
					<xsl:apply-templates/>
				</span>
				<xsl:if test="following-sibling::*">
					<xsl:text>, </xsl:text>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="volno">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="issno">
		<xsl:text> </xsl:text>
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="pp">
		<xsl:choose>
			<xsl:when test="parent::bb">
<!--
				<xsl:apply-templates/>
-->
			</xsl:when>
			<xsl:otherwise>
				<xsl:text> </xsl:text>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="ti">
		<xsl:choose>
			<xsl:when test="parent::ack">
				<div class="top-ref">
					<a href="#top">Back to Top</a>
				</div>
				<h1>
					<xsl:attribute name="id"><xsl:value-of select="'Ack'"/></xsl:attribute>
					<xsl:apply-templates/>
				</h1>
			</xsl:when>
			<xsl:when test="parent::bb">
<!--
				<xsl:text> </xsl:text>
				<i>
					<xsl:apply-templates/>
				</i>
-->
			</xsl:when>
			<xsl:when test="parent::index">
				<p/>
				<h2>
					<xsl:apply-templates/>
				</h2>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="fm/abs">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="au">
		<xsl:choose>
			<xsl:when test="parent::bb">
<!--
				<xsl:apply-templates select="fnm"/>
				<xsl:text> </xsl:text>
				<xsl:apply-templates select="snm"/>
				<xsl:if test="count(following-sibling::au) = 1 and  count(preceding-sibling::au) = 0 ">
					<xsl:text> </xsl:text>
				</xsl:if>
				<xsl:apply-templates select="child::*[not(self::fnm) and not(self::snm)]"/>
				<xsl:if test="count(following-sibling::au) > 1 
				  or ((count(following-sibling::au) = 0) 
				  or (count(following-sibling::obi) > 0 and count(preceding-sibling::au) = 1)) ">
					<xsl:text>, </xsl:text>
				</xsl:if>
-->
			</xsl:when>
			<xsl:when test="parent::index-entry">
				<b>
					<xsl:apply-templates select="snm"/>
				</b>
				<xsl:if test="child::appel">
					<b>
						<xsl:text> </xsl:text>
						<xsl:apply-templates select="child::*[not(self::fnm) and not(self::snm)]"/>
					</b>
				</xsl:if>
				<b>
					<xsl:apply-templates select="fnm"/>
				</b>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
				<br/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="au/fnm">
		<xsl:apply-templates/>
		<xsl:if test="following-sibling::snm">
			<xsl:text> </xsl:text>
		</xsl:if>
	</xsl:template>
	<xsl:template match="au/snm">
		<xsl:apply-templates/>
		<xsl:if test="not(following-sibling::appel) and following-sibling::*">
			<xsl:choose>
				<xsl:when test="following-sibling::aff">
					<xsl:text>, </xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text> </xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	<!-- The aff element does not seem to appear inside <au> in transactions, but
  we will assume that if it is present, then it should be rendered in the
  same way that the aff element is rendered in a magazine, and so we won't
  add any conditional logic to the following template rule -->
	<!-- Other elements that are supposed to be formatted in the same way as <aff> have
	been added to the template rule in accordance with our instructions.  -->
	<xsl:template match="aff | deg | role | ead | cny | odv | str | sbd | san | pc | cty">
		<xsl:if test="not(ancestor::bb)">
			<xsl:if test="preceding-sibling::ref and parent::au">
				<xsl:text> </xsl:text>
			</xsl:if>
			<xsl:apply-templates/>
			<xsl:if test="following-sibling::*">
				<xsl:text>, </xsl:text>
			</xsl:if>
		</xsl:if>
	</xsl:template>
	<!-- ****************************************************************
sec ELEMENTS
***************************************************************** -->
	<xsl:template match="no">
		<xsl:choose>
			<!-- We check to see if the <no> is inside a <tbl> and, at the same time, we check
	to see if we're processing a transaction or a magazine because in transactions
	the word TABLE needs to be in uppercase whereas it is not in magazines. -->
			<xsl:when test="parent::tbl">
				<xsl:text>Table </xsl:text>
				<xsl:copy-of select="text()"/>
				<xsl:text>. </xsl:text>
			</xsl:when>
			<xsl:when test="parent::bb">
			</xsl:when>
			<xsl:when test="parent::fig">
				<span class="captionText">
					<xsl:text>Figure </xsl:text>
					<xsl:copy-of select="text()"/>
					<xsl:text>. </xsl:text>
				</span>
			</xsl:when>
			<xsl:when test="ancestor::app">
			</xsl:when>
			<xsl:otherwise>
				<xsl:copy-of select="text()"/>
				<xsl:text> </xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="st">
		<xsl:if test="parent::sec">
			<xsl:choose>
				<xsl:when test="preceding-sibling::no">
					<div class="section">
						<xsl:attribute name="id">
							<xsl:value-of select="concat('section-', string(count(preceding::sec) + 1))"/>
						</xsl:attribute>
						<div class="section-title">
							<div>
								<xsl:attribute name="id">
									<xsl:choose>
										<xsl:when test="name(self::node())='h'">
											<xsl:value-of select="'Ref'"/>
										</xsl:when>
										<xsl:otherwise>
											<xsl:value-of select="concat('target', string(count(preceding::sec) + 1))"/>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:attribute>
								<xsl:if test="($pubtype='trans' or $pubtype='letters')">
									<xsl:value-of select="concat(preceding-sibling::no, '. ')"/>
								</xsl:if>
								<xsl:apply-templates/>
							</div>
						</div>
					</div>
				</xsl:when>
				<xsl:when test="position() = 1 and count(preceding::sec) = 0">
					<div id="section-t">
						<div class="section-title-t">
							<div>
								<xsl:attribute name="id">
									<xsl:choose>
										<xsl:when test="name(self::node())='h'">
											<xsl:value-of select="'Ref'"/>
										</xsl:when>
										<xsl:otherwise>
											<xsl:value-of select="concat('target', string(count(preceding::sec) + 1))"/>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:attribute>
								<xsl:choose>
									<xsl:when test="@cased">
										<xsl:value-of select="@cased"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:apply-templates/>
									</xsl:otherwise>
								</xsl:choose>
							</div>
						</div>
					</div>
				</xsl:when>
				<xsl:otherwise>
					<div class="section">
						<xsl:attribute name="id">
							<xsl:value-of select="concat('section-', string(count(preceding::sec) + 1))"/>
						</xsl:attribute>
						<div class="section-title">
							<div>
								<xsl:attribute name="id"><xsl:choose><xsl:when test="name(self::node())='h'"><xsl:value-of select="'Ref'"/></xsl:when><xsl:otherwise><xsl:value-of select="concat('target', string(count(preceding::sec) + 1))"/></xsl:otherwise></xsl:choose></xsl:attribute>
								<xsl:choose>
									<xsl:when test="@cased">
										<xsl:if test="($pubtype='trans' or $pubtype='letters')">
											<xsl:value-of select="count(parent::*/preceding-sibling::*) + 1"/>
											<xsl:text>. </xsl:text>
										</xsl:if>
										<xsl:value-of select="@cased"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:if test="($pubtype='trans' or $pubtype='letters')">
											<xsl:value-of select="count(parent::*/preceding-sibling::*) + 1"/>
											<xsl:text>. </xsl:text>
										</xsl:if>
										<xsl:apply-templates/>
									</xsl:otherwise>
								</xsl:choose>
							</div>
						</div>
					</div>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
		<xsl:if test="parent::ss1">
			<div class="ss1-heading">
				<xsl:apply-templates/>
			</div>
		</xsl:if>
		<xsl:if test="parent::ss2">
			<b>
				<xsl:apply-templates/>
			</b>
			<xsl:text> </xsl:text>
		</xsl:if>
		<xsl:if test="parent::ss3">
			<em>
				<xsl:apply-templates/>
			</em>
			<xsl:text> </xsl:text>
		</xsl:if>
	</xsl:template>

	<!-- The following match rule was extended to include ss3 in response to feedback which says
		 that the ss3 element should be formatted the same way as the ss2 element (though its child
		 st element is rendered in italic instead of bold - this difference is caught by the template
		 above that handles st). -->
	<xsl:template match="ss1">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="ss2">
		<br/>
		<span class="ss2-heading">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="ss3">
		<br/>
		<span class="ss3-heading">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="sec">
		<xsl:apply-templates/>
	</xsl:template>
	<!-- ****************************************************************
secmat ELEMENTS
***************************************************************** -->
	<!-- xsl:template match="bdy/ack" was changed to -->
	<xsl:template match="ack">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="bib">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="en">
		<p align="right">
			<xsl:copy-of select="text()"/>
		</p>
		<br clear="left"/>
	</xsl:template>
	<xsl:template match="footnote">
		<a>
			<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
		</a>
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="h">
		<xsl:choose>
			<xsl:when test="parent::index-entry">
				<div class="h3-heading">
					<xsl:apply-templates/>
				</div>
			</xsl:when>
			<xsl:when test="parent::sb">
				<h1>
					<xsl:apply-templates/>
				</h1>
			</xsl:when>
			<xsl:when test="parent::bibl">
				<h4>
					<xsl:attribute name="id"><xsl:value-of select="concat('Ref', string(count(preceding::bibl) + 1))"/></xsl:attribute>
					<xsl:apply-templates/>
				</h4>
			</xsl:when>
<!-- 
			<xsl:otherwise>
				<h4>
					<xsl:apply-templates/>
					<a name="acknowledgement"/>
				</h4>
			</xsl:otherwise>
-->
		</xsl:choose>
	</xsl:template>
	<xsl:template match="sec/p | sec/ip1 | ss1/p  | ss1/ip1">
		<div class="abs-content-large">
			<xsl:apply-templates/>
		</div>
	</xsl:template>
	<xsl:template match="ss2/p[1]">
		<xsl:choose>
			<xsl:when test="preceding-sibling::ip1">
				<p>
					<xsl:apply-templates/>
				</p>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="ss2/p[position()&gt;1]">
		<p>
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="ss2/ip1[1]">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="ss2/ip1[position()&gt;1]">
		<p>
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="fgc/p | fgc/ip1">
		<p>
			<span class="captionText">
				<xsl:apply-templates/>
			</span>
		</p>
		<br/>
	</xsl:template>
	<xsl:template match="fgc[p|ip1]">
		<span class="captionText">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="fgc[not(p|ip1)]">
		<span class="captionText">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="theorem/p | theorem/ip1 | proof/p | proof/ip1">
		<!-- NOTE: with theorems and proofs, head always comes first and there should not be a break with its following paragraph; as such the first p or ip1 is handled separately -->
		<p>
			<!-- if first occuring right after head, pull head's content in -->
			<xsl:choose>
				<xsl:when test="position()=1">
					<xsl:apply-templates select="preceding-sibling::head"/>
					<xsl:text> </xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="style">margin-left: 16pt</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<!-- if is a theorem, the text is italic -->
			<xsl:choose>
				<xsl:when test="parent::theorem">
					<em>
						<xsl:apply-templates/>
					</em>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates/>
				</xsl:otherwise>
			</xsl:choose>
		</p>
	</xsl:template>
	<xsl:template match="footnote/p | footnote/ip1">
		<p>
			<xsl:choose>
				<xsl:when test="position()=1">
					<xsl:attribute name="style">text-indent: -8pt; margin-left: 16pt</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="style">margin-left: 16pt</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="vt/p | vt/ip1">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="abs/p | abs/ip1">
		<p>
			<xsl:attribute name="class">abstract</xsl:attribute>
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="item/p | item/ip1">
		<!-- NOTE: indent within a list depends on the nesting level -->
		<p>
			<!-- if first occuring right after label, pull label's content in -->
			<xsl:choose>
				<xsl:when test="position()=1">
					<xsl:attribute name="style">text-indent: -<xsl:value-of select="count(ancestor::list) * 1 + 0"/>pt; margin-left: 16pt</xsl:attribute>
					<xsl:apply-templates select="preceding-sibling::label"/>
					<xsl:text> </xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="style">margin-left: 16pt</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<!-- if list is within a theorem, the text is italic -->
			<xsl:choose>
				<xsl:when test="ancestor::theorem">
					<em>
						<xsl:apply-templates/>
					</em>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates/>
				</xsl:otherwise>
			</xsl:choose>
		</p>
	</xsl:template>
	<xsl:template match="p[ancestor::le]">
		<!-- template rule to match p tags that come within li tags inside of an <le> list.  These lists are not formatted as
  	lists and do not contain line breaks between the different elemetns and so <p> tags are not added for them.  -->
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="p | ip1 | p2 | p3">
		<!-- template rule to match p tags that do not fall into any of the other categories (children of ack for example) -->
		<div class="abs-content-large">
			<xsl:apply-templates/>
		</div>
	</xsl:template>
	<xsl:template match="p1">
		<p style="text-indent:+.25in">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="head">
		<b>
			<xsl:apply-templates/>
		</b>
	</xsl:template>
	<xsl:template match="ref">
		<a>
			<xsl:choose>
				<xsl:when test="@type != 'prb'">
					<xsl:choose>
						<xsl:when test="@rid">
							<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@rid"/></xsl:attribute>
						</xsl:when>
						<xsl:when test="@fid">
							<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@fid"/></xsl:attribute>
						</xsl:when>
						<xsl:when test="@afid">
							<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@afid"/></xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:apply-templates/>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:choose>
						<xsl:when test="@type='fig' or @type='tbl'">
							<font color="red">
								<xsl:apply-templates/>
							</font>
						</xsl:when>
						<xsl:when test="@type='aff'">
							<sup>
								<b>
									<font color="#8E2323">
										<xsl:apply-templates/>
									</font>
								</b>
							</sup>
						</xsl:when>
						<xsl:when test="@type='fn'">
							<sup>
								<span style="color:red">
									<xsl:apply-templates/>
								</span>
							</sup>
						</xsl:when>
						<xsl:when test="@type='bib'">
							<sup>
								<xsl:apply-templates/>
							</sup>
						</xsl:when>
						<xsl:otherwise>
							<font color="blue">
								<xsl:apply-templates/>
							</font>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="@type = 'prb'">
					<xsl:choose>
						<xsl:when test="@rid">
							<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@rid"/></xsl:attribute>
							<font color="blue">
								<xsl:apply-templates/>
							</font>
						</xsl:when>
						<xsl:when test="@aid">
							<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@aid"/></xsl:attribute>
							<xsl:text> </xsl:text>
							<font color="blue">
								<xsl:apply-templates/>
							</font>
						</xsl:when>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates/>
				</xsl:otherwise>
			</xsl:choose>
		</a>
	</xsl:template>
	<!--
	<xsl:template match="ref[@type!='prb']">
		<a>
			<xsl:choose>
				<xsl:when test="@rid">
					<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@rid"/></xsl:attribute>
				</xsl:when>
				<xsl:when test="@fid">
					<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@fid"/></xsl:attribute>
				</xsl:when>
				<xsl:when test="@afid">
					<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@afid"/></xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates/>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:choose>
				<xsl:when test="@type='fig' or @type='tbl'">
					<font color="red">
						<xsl:apply-templates/>
					</font>
				</xsl:when>
				<xsl:when test="@type='aff'">
					<sup>
						<b>
							<font color="#8E2323">
								<xsl:apply-templates/>
							</font>
						</b>
					</sup>
				</xsl:when>
				<xsl:when test="@type='fn'">
					<sup>
						<span style="color:red">
							<xsl:apply-templates/>
						</span>
					</sup>
				</xsl:when>
				<xsl:when test="@type='bib'">
					<sup>
						<xsl:apply-templates/>
					</sup>
				</xsl:when>
			</xsl:choose>
		</a>
	</xsl:template>
	<xsl:template match="ref[@type='prb']">
		<xsl:choose>
			<xsl:when test="@rid">
				<a>
					<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@rid"/></xsl:attribute>
					<font color="blue">
						<xsl:apply-templates/>
					</font>
				</a>
			</xsl:when>
			<xsl:when test="@aid">
				<a>
					<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@aid"/></xsl:attribute>
					<xsl:text> </xsl:text>
					<font color="blue">
						<xsl:apply-templates/>
					</font>
				</a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
-->
	<!-- theorem and proof are combined since they're rendered identically -->
	<xsl:template match="theorem | proof">
		<xsl:choose>
			<!-- this is almost always be the case, but just to be safe... -->
			<xsl:when test="child::head and (name(child::*[position() = 2]) = 'p' or name(child::*[position() = 2]) = 'ip1')">
				<!-- don't process head at this level, let the first p or ip1 pull the head in -->
				<xsl:apply-templates select="*[not(self::head)]"/>
			</xsl:when>
			<xsl:otherwise>
				<p style="text-indent: -16pt; margin-left: 16pt">
					<xsl:apply-templates/>
				</p>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="vt">
		<div class="abs-content-large">
			<a>
				<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
			</a>
			<xsl:apply-templates/>
		</div>
	</xsl:template>
<!--
*****************************************************************
parmat ELEMENTS
sample: http://www.computer.org/cms/Computer.org/dl/glyphs/aacute.gif
*****************************************************************
-->
<xsl:template match="entityImg">
	<img style="margin-top:-3px">
	<xsl:attribute name="src">
		<xsl:value-of select="concat('http://www.computer.org/cms/Computer.org/dl/glyphs/', art/@file)"/>
	</xsl:attribute>
	<xsl:attribute name="border">0</xsl:attribute>
	<xsl:attribute name="align">absmiddle</xsl:attribute>
	<xsl:attribute name="alt"><xsl:value-of select="text()"/></xsl:attribute>
	</img>
</xsl:template>

	<xsl:template match="tf">
		<xsl:variable name="tf_1">
			<xsl:value-of select="text()"/>
		</xsl:variable>
		<xsl:variable name="tf_2">
			<xsl:choose>
				<xsl:when test="contains($tf_1, &quot;\&quot;)">
					<xsl:call-template name="strreplace">
						<xsl:with-param name="string" select="$tf_1"/>
						<xsl:with-param name="search">\</xsl:with-param>
						<xsl:with-param name="replace">\\</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="contains($tf_1, &quot;'&quot;)">
					<xsl:call-template name="strreplace">
						<xsl:with-param name="string" select="$tf_1"/>
						<xsl:with-param name="search">'</xsl:with-param>
						<xsl:with-param name="replace">\'</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$tf_1"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<a href="javascript:void();">
			<xsl:attribute name="onclick"><xsl:text>javascript:showText('mathPopup','</xsl:text><xsl:value-of select="$tf_2"/><xsl:text>');</xsl:text></xsl:attribute>
			<br/>
			<img>
				<xsl:attribute name="src">http://www.computer.org/<xsl:value-of select="concat('/cms/Computer.org/dl/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/figures/', translate(art/@file, $UPPERCASE, $lowercase))"/></xsl:attribute>
				<xsl:attribute name="border">0</xsl:attribute>
				<xsl:attribute name="align">absmiddle</xsl:attribute>
				<xsl:attribute name="alt"><xsl:value-of select="text()"/></xsl:attribute>
			</img>
		</a>
		<br/>
	</xsl:template>
	<xsl:template match="enum">
		<xsl:choose>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 1 and @t='Aa'">
				<xsl:text>a</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 2 and @t='Aa'">
				<xsl:text>b</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 3 and @t='Aa'">
				<xsl:text>c</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 4 and @t='Aa'">
				<xsl:text>d</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 5 and @t='Aa'">
				<xsl:text>e</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 6 and @t='Aa'">
				<xsl:text>f</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 7 and @t='Aa'">
				<xsl:text>g</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 8 and @t='Aa'">
				<xsl:text>h</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 9 and @t='Aa'">
				<xsl:text>i</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 10 and @t='Aa'">
				<xsl:text>j</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 1 and @t='Ii'">
				<xsl:text>i</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 2 and @t='Ii'">
				<xsl:text>ii</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 3 and @t='Ii'">
				<xsl:text>iii</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 4 and @t='Ii'">
				<xsl:text>iv</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 5 and @t='Ii'">
				<xsl:text>v</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 6 and @t='Ii'">
				<xsl:text>vi</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 7 and @t='Ii'">
				<xsl:text>vii</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 8 and @t='Ii'">
				<xsl:text>viii</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 9 and @t='Ii'">
				<xsl:text>ix</xsl:text>
			</xsl:when>
			<xsl:when test="count(parent::label/parent::item/preceding-sibling::item) + 1 = 10 and @t='Ii'">
				<xsl:text>x</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="count(parent::label/parent::item/preceding-sibling::item) + 1"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="b">
		<xsl:copy>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="sub">
		<xsl:copy>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="super">
		<sup>
			<xsl:apply-templates/>
		</sup>
	</xsl:template>
	<xsl:template match="it">
		<em>
			<xsl:apply-templates/>
		</em>
	</xsl:template>
	<xsl:template match="br">
		<br>
			<xsl:apply-templates/>
		</br>
	</xsl:template>
	<xsl:template match="scp">
		<xsl:apply-templates/>
	</xsl:template>
	<!-- ariel was added to the following rule since it is meant to be formatted in the same way as ss according to the feedback we received.  -->
	<xsl:template match="ss">
		<span style="font-family:arial,helvetica,sans-serif">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="ariel">
		<span style="font-family:arial,helvetica,sans-serif">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<!-- inline tags added to handle elements that are used in magazines but that were not present
in the sample transactions file (cstrans.xml) -->
	<!--Change made by Edna on 26 Jan 2007 to have <url>s made into links for issue with a publication year of 2007. -->
	<xsl:template match="url">

		<xsl:choose>
			<xsl:when test="($PubYear='2009') or ($PubYear='2096')">

				<a>
					<xsl:choose>
						<xsl:when test="substring(text(),1,5)='http:'">
							<xsl:attribute name="href"><xsl:value-of select="text()"/></xsl:attribute>
						</xsl:when>
						<xsl:when test="substring(text(),1,4)='ftp:'">
							<xsl:attribute name="href"><xsl:value-of select="text()"/></xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:attribute name="href"><xsl:value-of select="concat('http://',text())"/></xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:attribute name="target">blank</xsl:attribute>
					<xsl:apply-templates/>
				</a>

			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>

	</xsl:template>
	<xsl:template match="bq">
		<blockquote>
			<xsl:apply-templates/>
		</blockquote>
	</xsl:template>
	<xsl:template match="fgb">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="p/lit | fgc/lit">
		<span class="monospace">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="fgb/lit" priority="1">
		<xsl:choose>
			<xsl:when test="parent::fig">
				<pre>
					<xsl:apply-templates/>
				</pre>
			</xsl:when>
			<xsl:otherwise>
				<pre>
					<xsl:apply-templates/>
				</pre>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="lit">
		<pre>
			<xsl:apply-templates/>
		</pre>
	</xsl:template>
	<xsl:template match="algorithm">
		<xsl:choose>
			<xsl:when test="preceding-sibling::st">
				<div class="html-abstract">
					<pre style="color: navy; background-color: transparent; padding: 10pt; width: 700px; max-height:500px; min-height:50px; overflow: auto; border: solid 1px">
						<xsl:apply-templates/>
					</pre>
				</div>
			</xsl:when>
			<xsl:otherwise>
				<pre style="color: navy; background-color: transparent; padding: 10pt; width: 700px; max-height:500px; min-height:50px; overflow: auto; border: solid 1px">
					<xsl:apply-templates/>
				</pre>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="loc">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="h1a">
		<h2>
			<xsl:apply-templates/>
		</h2>
	</xsl:template>
	<xsl:template match="apt">
		<h1>
			<font size="+1">
				<xsl:apply-templates/>
			</font>
		</h1>
	</xsl:template>
	<!-- TODO check this rule against g2066.xml which I don't have yet -->
	<xsl:template match="bhead">
		<h2>
			<xsl:apply-templates/>
		</h2>
	</xsl:template>
	<!-- *****************************************************************
BACK MATTER ELEMENTS
*****************************************************************  -->
	<xsl:template match="app">
		<xsl:variable name="thisID">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<a>
			<xsl:attribute name="name"><xsl:value-of select="$thisID"/></xsl:attribute>
		</a>
		<div class="sidebar">
			<xsl:apply-templates/>
		</div>
	</xsl:template>
	<xsl:template match="bibl">
		<div class="section">
			<div class="section-title">
				<xsl:attribute name="id"><xsl:value-of select="concat('Ref', string(count(preceding::bibl) + 1))"/></xsl:attribute>
			</div>
			<xsl:apply-templates/>
		</div>
<!--
		<div>
			<xsl:attribute name="id"><xsl:value-of select="concat('framediv', string(count(preceding::bibl) + 1))"/></xsl:attribute>
			<iframe frameborder="no" width="0" height="0" scrolling="no" >
			<xsl:attribute name="id"><xsl:value-of select="concat('bibframe', string(count(preceding::bibl) + 1))"/></xsl:attribute>
			<xsl:attribute name="onload"><xsl:value-of select="'insertIt('" /><xsl:text>'</xsl:text><xsl:value-of select="concat('framediv', string(count(preceding::bibl) + 1))"/><xsl:text>', '</xsl:text><xsl:value-of select="concat('bibframe', string(count(preceding::bibl) + 1))"/><xsl:text>');</xsl:text></xsl:attribute>
			<xsl:attribute name="src"><xsl:value-of select="concat('/cms/Computer.org/dl/bibrefs/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/', $PubType, $PubAcro, $PubYear, $PubIss, $StartPg, '_', string(count(preceding::bibl) + 1), '.htm')"/></xsl:attribute>
			</iframe>
		</div> 
-->
	</xsl:template>
	<xsl:template match="atl">
		<!-- xsl:if test="not(ancestor::bb)" -->
		<xsl:apply-templates/>
		<!-- /xsl:if -->
	</xsl:template>
	<xsl:template match="sbt">
		<br/>
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="mo">
		<xsl:apply-templates/>
		<xsl:text> </xsl:text>
	</xsl:template>
	<xsl:template match="yr">
		<xsl:choose>
			<xsl:when test="ancestor::bb">
<!--
				<xsl:text> </xsl:text>
				<xsl:apply-templates/>
-->
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="obi">
		<xsl:choose>
			<xsl:when test="ancestor::bb">
<!--
				<xsl:text> </xsl:text>
				<xsl:apply-templates/>
				<xsl:text> </xsl:text>
-->
			</xsl:when>
			<xsl:otherwise>
				<xsl:text> </xsl:text>
				<xsl:apply-templates/>
				<xsl:text> </xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- ****************************************************************
secparmat ELEMENTS
***************************************************************** -->

	<xsl:template match="bb">
	</xsl:template>
<!--
	<xsl:template match="bb">
		<xsl:variable name="thisID">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:variable name="elValue">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<a>
			<xsl:attribute name="name"><xsl:value-of select="$thisID"/></xsl:attribute>
		</a>
		<li class="bibRef">

			<xsl:if test="$PubType = 'm'">
			   <xsl:text>[</xsl:text>
			</xsl:if>

	     	<xsl:choose>
		   		<xsl:when test="string(number(substring($thisID,$bbIdLength, 1))) != 'NaN'">
		   			<xsl:value-of select="substring($thisID,number($bbIdLength))"/>
		   		</xsl:when>
		   		<xsl:otherwise>
		   			<xsl:value-of select="substring($thisID,number($bbIdLength)-1)"/>
		   		</xsl:otherwise>
		   	</xsl:choose>

			<xsl:choose>
				<xsl:when test="$PubType = 'm'">
				   <xsl:text>] </xsl:text>
				</xsl:when>
				<xsl:when test="$PubType = 't'">
				   <xsl:text>. </xsl:text>
				</xsl:when>
			</xsl:choose>
		   <xsl:apply-templates/>
		</li>
	</xsl:template>
-->
	<xsl:template match="fig[@id] | figw[@id]">
		<a>
			<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
		</a>
		<xsl:apply-templates/>
		<!-- Added for ex/2004/06/x6049 figure 3 -->
		<br clear="left"/>
		<br clear="left"/>
	</xsl:template>
	<xsl:template match="fig[not(@id)] | figw[not(@id)]">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="bm/vt/fig/art | bm/vt/p/fig/art" priority="1">
		<a>
			<xsl:attribute name="href">http://www.computer.org/<xsl:value-of select="concat('/cms/Computer.org/dl/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/figures/' , translate(@file, $UPPERCASE, $lowercase))"/></xsl:attribute>
			<p>
				<img class="wrap" hspace="15" align="left" border="0">
					<xsl:attribute name="src">http://www.computer.org/<xsl:value-of select="concat('/cms/Computer.org/dl/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/figures/figthmnl/', translate(@file, $UPPERCASE, $lowercase))"/></xsl:attribute>
					<xsl:attribute name="width"><xsl:value-of select="number(@tw)*.80"/></xsl:attribute>
					<xsl:attribute name="height"><xsl:value-of select="number(@th)*.80"/></xsl:attribute>
				</img>
			</p>
		</a>
	</xsl:template>
	<xsl:template match="art">
		<a target="_blank">
			<xsl:attribute name="href">http://www.computer.org/<xsl:value-of select="concat('/cms/Computer.org/dl/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/figures/', translate(@file, $UPPERCASE, $lowercase))"/></xsl:attribute>
			<br clear="left"/>
			<p>
				<img hspace="15" align="left" border="0">
					<xsl:attribute name="src">http://www.computer.org/<xsl:value-of select="concat('/cms/Computer.org/dl/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/figures/figthmnl/', translate(@file, $UPPERCASE, $lowercase))"/></xsl:attribute>
					<xsl:attribute name="width"><xsl:value-of select="@tw"/></xsl:attribute>
					<xsl:attribute name="height"><xsl:value-of select="@th"/></xsl:attribute>
				</img>
			</p>
			<br clear="left"/>
		</a>
	</xsl:template>
	<xsl:template match="tmath">
		<xsl:variable name="tmath_1">
			<xsl:value-of select="text()"/>
		</xsl:variable>
		<xsl:variable name="tmath_2">
			<xsl:choose>
				<xsl:when test="contains($tmath_1, &quot;\&quot;)">
					<xsl:call-template name="strreplace">
						<xsl:with-param name="string" select="$tmath_1"/>
						<xsl:with-param name="search">\</xsl:with-param>
						<xsl:with-param name="replace">\\</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="contains($tmath_1, &quot;'&quot;)">
					<xsl:call-template name="strreplace">
						<xsl:with-param name="string" select="$tmath_1"/>
						<xsl:with-param name="search">'</xsl:with-param>
						<xsl:with-param name="replace">\'</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$tmath_1"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<a href="javascript:void();">
			<xsl:attribute name="onclick"><xsl:text>javascript:showText('mathPopup','</xsl:text><xsl:value-of select="$tmath_2"/><xsl:text>');</xsl:text></xsl:attribute>
			<img>
				<xsl:attribute name="src">http://www.computer.org/<xsl:value-of select="concat('/cms/Computer.org/dl/', $pubtype, '/', $PubAcro, '/', $PubYear, '/', $PubIss, '/figures/', translate(art/@file, $UPPERCASE, $lowercase))"/></xsl:attribute>
				<xsl:attribute name="border">0</xsl:attribute>
				<xsl:attribute name="align">absmiddle</xsl:attribute>
				<xsl:attribute name="alt"><xsl:value-of select="text()"/></xsl:attribute>
				<xsl:attribute name="width"><xsl:value-of select="art/@w"/></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="art/@h"/></xsl:attribute>
			</img>
		</a>
	</xsl:template>
	<xsl:template match="pdt">
		<xsl:apply-templates/>
	</xsl:template>
	<!-- ****************************************************************
LIST AND LIST ITEM ELEMENTS
***************************************************************** -->
	<xsl:template match="list">
		<div class="list-div">
			<ul>
				<xsl:choose>
					<xsl:when test="@type='num'">
						<xsl:choose>
							<xsl:when test="descendant::enum[position()=1][attribute::t='1']">
								<xsl:attribute name="style">list-type: decimal</xsl:attribute>
							</xsl:when>
							<xsl:when test="descendant::enum[position()=1][attribute::t='Aa']">
								<xsl:attribute name="style">list-type: lower-alpha</xsl:attribute>
							</xsl:when>
							<xsl:when test="descendant::enum[position()=1][attribute::t='A']">
								<xsl:attribute name="style">list-type: upper-alpha</xsl:attribute>
							</xsl:when>
							<xsl:when test="descendant::enum[position()=1][attribute::t='I']">
								<xsl:attribute name="style">list-type: upper-roman</xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="style">list-type: decimal</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:when test="@type='bull'">
						<xsl:attribute name="style">list-type: disc</xsl:attribute>
					</xsl:when>
					<!-- TODO determine what to do with lists of type 'outd' -->
					<xsl:when test="@type='outd'">
						<xsl:attribute name="style">list-type: disc</xsl:attribute>
					</xsl:when>
					<xsl:when test="@type='plain'">
						<xsl:attribute name="style">list-type: none</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">list-type: none</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="bullet-list">
		<div class="list-div">
			<ul style="list-style: disc">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="numeric-list">
		<div class="list-div">
			<ul style="list-style: decimal">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="numeric-rbrace">
		<div class="list-div">
			<ul style="list-style: decimal">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l1">
		<div class="list-div">
			<ul style="list-style: disc">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l2">
		<div class="list-div">
			<ul style="list-style: decimal">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l3">
		<div class="list-div">
			<ul style="list-style: none">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l4">
		<div class="list-div">
			<ul style="list-style: lower-alpha">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l5">
		<div class="list-div">
			<ul style="list-style: decimal">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l6">
		<div class="list-div">
			<ul style="list-style: disc">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l7">
		<div class="list-div">
			<ul style="list-style: lower-roman">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l8">
		<div class="list-div">
			<ul style="list-style: disc">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="l9">
		<div class="list-div">
			<ul style="list-style: disc">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="lb">
		<div class="list-div">
			<ul style="list-style: plain">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="lc">
		<div class="list-div">
			<ul style="list-style: disc">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="ld">
		<div class="list-div">
			<ul style="list-style: plain">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<xsl:template match="le">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="la">
		<div class="list-div">
			<ul style="list-style: decimal">
				<xsl:apply-templates/>
			</ul>
		</div>
	</xsl:template>
	<!-- indent level of list item depends on level of nesting -->
	<xsl:template match="item">
		<xsl:choose>
			<!-- this should always be the case, but just to be safe... -->
			<xsl:when test="child::label and (name(child::*[position() = 2]) = 'p' or name(child::*[position() = 2]) = 'ip1')">
				<!-- don't process the label at this level, let the first p or ip1 pull the label in -->
				<xsl:apply-templates select="*[not(self::label)]"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="label">
		<xsl:apply-templates/>
	</xsl:template>
	<!-- New template rules were added for all the different types of list item.  Ideally the properties for
	the lists should be set at the highest level (list), these values for the individual items will
	override the setting on the list as a whole - but that's unavoidable given the DTD structure.  -->
	<xsl:template match="item-bold">
		<li style="list-style: none">
			<!-- TODO figure out where the "number" for the item-bold is supposed to be; i.e. which attribute is it?  -->
			<span style="font-weight: bold">
				<xsl:value-of select="@t"/>
				<xsl:value-of select="@text"/>
			</span>
			<xsl:text> </xsl:text>
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-both">
		<li style="list-style: decimal">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-bullet">
		<li style="list-style: disc">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-diamond">
		<li style="list-style: circle">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-letpara">
		<li style="list-style: lower-alpha">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-mdash">
		<li style="list-style: none">&#x2014;<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-none">
		<li style="list-style: none">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-numpara">
		<li style="list-style: decimal">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-roman">
		<li style="list-style: lower-roman">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="item-text">
		<li style="list-style: none">
			<xsl:value-of select="@text"/>
			<xsl:text> </xsl:text>
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="li[parent::list[@type='outd']][position()=1]">
		<li style="margin-left: -.2in">
			<xsl:text>Margin left -.2 in</xsl:text>
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="li[parent::list[@type='outd']][position()&gt;1]">
		<li>
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="li[parent::lb]">
		<li>
			<xsl:value-of select="@t"/>
			<xsl:text> </xsl:text>
			<xsl:apply-templates/>
		</li>
	</xsl:template>
	<xsl:template match="li[parent::le]">
		<!-- The children of le do not get formatted as a list at all -->
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="li">
		<li style="list-style: disc">
			<xsl:apply-templates/>
		</li>
	</xsl:template>
<!--  changed to disc as some li's where bulleted in the PDF
		<li>
			<xsl:apply-templates/>
		</li>
-->

	<!--
*****************************************************************
INDEX-RELATED ELEMENTS
*****************************************************************
-->
	<xsl:template match="index">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="index-entry[au]">
		<p style="text-indent: -16pt; margin-left: 16pt">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="index-entry[not(au)]">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="term[not(preceding-sibling::au)]">
		<p style="text-indent: -16pt; margin-left: 16pt">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="term[preceding-sibling::au]">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="oau">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="see">
		<em>
			<xsl:text>see </xsl:text>
		</em>
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="also">
		<em>
			<xsl:text>see also </xsl:text>
		</em>
		<xsl:apply-templates/>
	</xsl:template>
	<!--
*****************************************************************
REVIEWER THANKS-RELATED ELEMENTS
*****************************************************************
-->
	<xsl:template match="reviewers">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="reviewers/reviewer[1]">
		<p style="margin-top:7%;margin-bottom:1%">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="reviewers/reviewer[position()&gt;1]">
		<p style="margin-bottom:1%">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="name">
		<b>
			<xsl:apply-templates/>
		</b>
		<xsl:text>, </xsl:text>
	</xsl:template>
	<xsl:template match="addr">
		<em>
			<xsl:apply-templates/>
		</em>
	</xsl:template>
	<!-- ****************************************************************
NEWLY ADDED ELEMENTS
***************************************************************** -->
	<xsl:template match="a">
		<xsl:copy-of select="."/>
	</xsl:template>
	<xsl:template match="appel">
		<xsl:apply-templates/>
		<xsl:if test="following-sibling::*">
			<xsl:text>, </xsl:text>
		</xsl:if>
	</xsl:template>
	<xsl:template match="answer">
		<xsl:apply-templates/>
	</xsl:template>
	<!-- The contents of edinfo are not being displayed in HTML at this time... don't ask me why  -->
	<xsl:template match="edinfo"/>
	<xsl:template match="kwd"/>
	<xsl:template match="bi">
		<b>
			<i>
				<xsl:apply-templates/>
			</i>
		</b>
	</xsl:template>
	<!-- According to the specifications from IEEE, the brief tag should be formatted:
	like <ip1>, but with line-space before and after  
  this interpretation has been taken literally (since <brief> can only contain <p> 
  tags, we have added a line break before and after and then passed on the contents
  to the default handler for <p> tags.  -->
	<xsl:template match="brief">
		<br/>
		<xsl:apply-templates/>
		<br/>
	</xsl:template>
	<xsl:template match="bu">
		<span style="text-decoration: underline;font-weight: bold">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="bui">
		<span style="text-decoration: underline;font-weight: bold;font-style: italic">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<!-- Apparently the <cen> tag was originally for centered text but is actually formatted as left-aligned 
  text.  I'm not sure if it should be treated as a separate block or not - for now it isn't.  -->
	<xsl:template match="cen">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="couplet">
		<blockquote>
			<xsl:apply-templates/>
		</blockquote>
	</xsl:template>
	<xsl:template match="crt">
		<xsl:if test="preceding-sibling::ti">
			<xsl:text> </xsl:text>
		</xsl:if>
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="day">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="dd">
		<xsl:apply-templates/>
		<br/>
	</xsl:template>
	<xsl:template match="ddhd">
		<b>
			<xsl:apply-templates/>
		</b>
		<br/>
	</xsl:template>
	<xsl:template match="dialog">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="dl">
		<!-- Although it represents a list, the dt/dd tags are rendered simply as spans on separate lines
  	in order to display the way that they are described by IEEE.  -->
		<br/>
		<xsl:apply-templates/>
	</xsl:template>
	<!-- The rule for 
ref makes 2 assumptions: 
  1. that only one of the three possible attributes will be provided for any given doiref
  2. that the value of the attribute used will be a legitimate URL. -->
	<xsl:template match="doiref">
		<xsl:apply-templates select="@*"/>
	</xsl:template>
	<xsl:template match="doiref/@doirefid">
		<a>
			<xsl:attribute name="href"><xsl:value-of select="."/></xsl:attribute>
			<xsl:value-of select="."/>
		</a>
		<xsl:text> </xsl:text>
	</xsl:template>
	<xsl:template match="doiref/@csrefid">
		<a>
			<xsl:attribute name="href"><xsl:value-of select="."/></xsl:attribute>
			<xsl:value-of select="."/>
		</a>
		<xsl:text> </xsl:text>
	</xsl:template>
	<xsl:template match="doiref/@ieeereaid">
		<a>
			<xsl:attribute name="href"><xsl:value-of select="."/></xsl:attribute>
			<xsl:value-of select="."/>
		</a>
		<xsl:text> </xsl:text>
	</xsl:template>
	<xsl:template match="dt">
		<b>
			<xsl:apply-templates/>
		</b>
		<xsl:text> </xsl:text>
	</xsl:template>
	<!-- The dthd element is meant to be handled the same way as apt (an H2 in blue) -->
	<xsl:template match="dthd">
		<span style="font-weight:bold;color: blue;font-size: +2pt">
			<xsl:apply-templates/>
		</span>
		<xsl:text> </xsl:text>
	</xsl:template>
	<xsl:template match="f">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="fn">
		<a>
			<xsl:attribute name="href"><xsl:text>#</xsl:text><xsl:value-of select="@id"/></xsl:attribute>
			<sup>
				<span style="color:red">
					<xsl:apply-templates/>
				</span>
			</sup>
		</a>
	</xsl:template>
	<xsl:template match="fno">
		<!-- Apparently the contents of the <fno> tag are not rendered in HTML but they do appear in metadata.  
   The contents of fno should already be caught explicitly in the template rule for front matter (fm) above. -->
	</xsl:template>
	<xsl:template match="h1">
		<p style="font-size:+1;font-weight:bold">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="h2">
		<h2 style="color:8E2323">
			<xsl:apply-templates/>
		</h2>
	</xsl:template>
	<!-- Based on feedback from the IEEE, it seems that h2a should be rendered as a block whereas h3 and h4
	elements should be treated as inline bold headings.  -->
	<xsl:template match="h2a">
		<p style="font-weight:bold">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="h3 | h4">
		<b>
			<xsl:apply-templates/>
		</b>
	</xsl:template>
	<xsl:template match="ilrj">
		<div style="text-align: center">
			<xsl:apply-templates/>
		</div>
	</xsl:template>
	<xsl:template match="ip2 | ip3">
		<p style="margin-left:+.25in">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="ip4">
		<p style="margin-left:+.5in">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="ip5">
		<p style="margin-left:+.75in">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="large">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="line">
		<blockquote>
			<xsl:apply-templates/>
		</blockquote>
	</xsl:template>
	<xsl:template match="math">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="pn">
		<!-- The contents of pn are not supposed to be displayed in HTML so this template is left blank.  -->
	</xsl:template>
	<xsl:template match="poetry">
		<blockquote>
			<xsl:apply-templates/>
		</blockquote>
	</xsl:template>
	<xsl:template match="pubfm">
		<!-- The contents of pn are not supposed to be displayed in HTML so this template is left blank.  The
  	contents of this tag are used in a meta tag, but this is gathered by the template rule for fm. -->
	</xsl:template>
	<xsl:template match="question">
		<em>
			<xsl:apply-templates/>
		</em>
	</xsl:template>
	<xsl:template match="rm">
		<font style="font-style:normal">
			<xsl:apply-templates/>
		</font>
	</xsl:template>
	<xsl:template match="rom">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="sgmlf">
		<p>
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<xsl:template match="sgmlmath">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="stanza">
		<blockquote>
			<xsl:apply-templates/>
		</blockquote>
	</xsl:template>
	<xsl:template match="thide">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="top1">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="top2">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="tt">
		<span class="monospace">
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<xsl:template match="u">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="ub">
		<xsl:apply-templates/>
	</xsl:template>
	<!-- ****************************************************************
TABLE ELEMENTS
***************************************************************** -->
	<!-- The tbl tag is used to add tables either as tagged tables (in which case they contain tgroup and 
	the template rule for tgroup generates the table) or as images (in which case they contain an
	art tag and the template rule for art generates the image of the table).   -->
<!--	
	<xsl:template match="tbody">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="colspec">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="spanspec">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="tgroup">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="row">
		<xsl:apply-templates/>
	</xsl:template>
-->
	<xsl:template match="tbl">
		<a>
			<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
		</a>
		<p style="color: green">
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<!-- In order to render tables that use markup (found in legacy data), the separate file tables.xsl is brought
	into this stylesheet in an xsl:include statement at the top of this file.  All the rules for the table
	elements are found in this separate file except for the rule that matches the <tfoot> tag since the 
	tfoot element is not present in CALS and is used to contain <p> elements that include the footnotes
	from the table.  They are rendered according to the rule for <p> elements. -->
	<!-- The tfoot tag contains <p> elements, but it is within the <tgroup> tag, so we've added a row around it.  -->
	<xsl:template match="tfoot">
		<tfoot>
			<tr>
				<td colspan="20">
					<xsl:apply-templates/>
				</td>
			</tr>
		</tfoot>
	</xsl:template>
	<xsl:template match="tfeet">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template name="strreplace">
		<xsl:param name="string"/>
		<xsl:param name="search"/>
		<xsl:param name="replace"/>
		<xsl:choose>
			<xsl:when test="contains($string, $search)">
				<xsl:variable name="rest">
					<xsl:call-template name="strreplace">
						<xsl:with-param name="string" select="substring-after($string, $search)"/>
						<xsl:with-param name="search" select="$search"/>
						<xsl:with-param name="replace" select="$replace"/>
					</xsl:call-template>
				</xsl:variable>
				<xsl:value-of select="concat(substring-before($string, $search), $replace, $rest)"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$string"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- ****************************************************************
		CATCH-ALL RULE
		***************************************************************** -->
	<!-- **	Comment the following line if you don't care about undocumented tags that are not implemented in the stylesheet.
				<H6>Unimplemented element: <xsl:value-of select="name(self::*)"/></H6>
					<xsl:apply-templates/>
			##-->
	<xsl:template match="*">
		<xsl:message>Unimplemented element: <xsl:value-of select="name(self::*)"/>
		</xsl:message>
	</xsl:template>

</xsl:transform>
