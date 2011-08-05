<!DOCTYPE html>

<#include init/>

<html class="<@liferay.language key="lang.dir" />" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	${theme.include(top_head_include)}
</head>

<body class="${css_class}">

<#if is_signed_in>
	<@liferay.dockbar/>
</#if>

<div id="wrapper">
	<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>

	<header id="banner" role="banner">
		<hgroup id="heading">
			<h1 class="company-title">
				<a class="logo" href="${company_url}" title="<@liferay.language key="go-to" /> ${company_name}">
					<span>${company_name}</span>
				</a>
			</h1>

			<h2 class="community-title">
				<a href="${community_default_url}" title="<@liferay.language key="go-to" /> ${community_name}">
					<span>${community_name}</span>
				</a>
			</h2>

			<h3 class="page-title">
				<span>${the_title}</span>
			</h3>
		</hgroup>

		<#if !is_signed_in>
			<a href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if update_available_url??>
			<div class="popup-alert-notice">
				<a class="update-available" href="${update_available_url}"><@liferay.language key="updates-are-available-for-liferay" /></a>
			</div>
		</#if>

		<#if has_navigation>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</header>

	<div id="content">
		<nav class="site-breadcrumbs" id="breadcrumbs">
			<h1>
				<span><@liferay.language key="breadcrumbs" /></span>
			</h1>

			<@liferay.breadcrumbs/>
		</nav>

		<#if selectable>
			${theme.include(content_include)}
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			${theme.wrapPortlet("portlet.ftl", content_include)}
		</#if>
	</div>

	<footer id="footer" role="contentinfo">
		<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>
</div>

</body>

${theme.include(bottom_include)}

</html>