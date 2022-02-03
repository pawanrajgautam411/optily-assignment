CREATE TABLE `campaign_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE `campaign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `budget` decimal(19,2) DEFAULT NULL,
  `impressions` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `revenue` decimal(19,2) DEFAULT NULL,
  `campaign_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7digsvjx1jyl4gxr9yudonetk` (`campaign_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

