/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mail.vaadin;

import com.liferay.mail.MailException;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.MailFile;
import com.liferay.mail.model.Message;
import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Henri Sara
 */
public class Composer extends CustomComponent {

	private static Log _log = LogFactoryUtil.getLog(Composer.class);

	private static final Object ACCOUNT_NAME_ID = "address";

	private List<AttachmentLayout> attachments = new ArrayList<AttachmentLayout>();

	private LinkedHashSet<ComposerListener> listeners =
		new LinkedHashSet<ComposerListener>();

	// UI
	private VerticalLayout mainLayout;
	private VerticalLayout innerLayout;
	private HorizontalLayout actionsLayout;
	private Button send;
	private Button save;
	private Button cancel;
	private VerticalLayout messageLayout;
	private RichTextArea message;
	private VerticalLayout bottomLayout;
	private HorizontalLayout subjectLayout;
	private Button addAttachment;
	private TextField subject;
	private TextField toField;
	private TextField ccField;
	private TextField bccField;
	private FormLayout recipientsLayout;
	private HorizontalLayout fromLayout;
	private ComboBox from;
	private long draftMessageId = 0;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization. The constructor will not be
	 * automatically regenerated by the visual editor.
	 */
	public Composer() {

		// The layout was originally composed with the visual editor and later
		// converted to hand-edited form.

		buildMainLayout();
		setCompositionRoot(mainLayout);
		mainLayout.setMargin(false);

		fromLayout.setMargin(true);
		from.setNullSelectionAllowed(false);
		initAccounts();

		innerLayout.setMargin(true);
		innerLayout.setHeight("100.0%");
		mainLayout.setExpandRatio(innerLayout, 1.0f);

		// Recipients
		recipientsLayout.setMargin(false, false, true, false);

		// Subject
		subjectLayout.setMargin(false);
		subjectLayout.setSpacing(true);
		subject.setInputPrompt(Lang.get("subject"));
		subjectLayout.setExpandRatio(subject, 1.0f);

		// Message
		innerLayout.setExpandRatio(messageLayout, 1.0f);

		bottomLayout.setMargin(true);
		bottomLayout.setSpacing(true);

		// Controls
		actionsLayout.setExpandRatio(cancel, 1.0f);
		actionsLayout.setComponentAlignment(cancel, "right");
		actionsLayout.setSpacing(true);
		actionsLayout.setMargin(true);

		cancel.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {

				final ConfirmDialog confirm =
					new ConfirmDialog(
						Lang.get("confirm"), Lang.get("discard-draft"),
						Lang.get("are-you-sure-you-want-to-discard-draft"));

				confirm.addConfirmButtonListener(new ClickListener() {

					public void buttonClick(ClickEvent event) {

						confirm.closeDialog();
						fireDiscard();
					}
				});

				Controller.get().getApplication().getMainWindow().addWindow(confirm);
			}
		});
		save.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {

				fireSave(false);
			}
		});
		send.addListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {

				fireSend(false);
			}
		});
	}

	private VerticalLayout buildMainLayout() {

		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setHeight("100%");

		// top-level component properties
		setHeight("-1px");
		setWidth("100.0%");
		setHeight("100.0%");

		// fromLayout
		fromLayout = buildFromLayout();
		fromLayout.setStyleName("black");
		fromLayout.setImmediate(false);
		fromLayout.setHeight("-1px");
		fromLayout.setWidth("100.0%");
		mainLayout.addComponent(fromLayout);

		// innerLayout
		innerLayout = new VerticalLayout();
		innerLayout.setImmediate(false);
		innerLayout.setHeight("-1px");
		innerLayout.setWidth("100.0%");
		mainLayout.addComponent(innerLayout);

		// recipientsLayout
		recipientsLayout = buildRecipientsLayout();
		recipientsLayout.setImmediate(false);
		recipientsLayout.setHeight("-1px");
		recipientsLayout.setWidth("100.0%");
		innerLayout.addComponent(recipientsLayout);

		// subjectLayout
		subjectLayout = buildSubjectLayout();
		subjectLayout.setStyleName("blue");
		subjectLayout.setImmediate(false);
		subjectLayout.setHeight("-1px");
		subjectLayout.setWidth("100.0%");
		innerLayout.addComponent(subjectLayout);

		// messageLayout
		messageLayout = buildMessageLayout();
		messageLayout.setImmediate(false);
		messageLayout.setHeight("100.0%");
		messageLayout.setWidth("100.0%");
		innerLayout.addComponent(messageLayout);

		// bottomLayout
		bottomLayout = new VerticalLayout();
		bottomLayout.setSpacing(true);
		bottomLayout.setStyleName("blue");
		bottomLayout.setWidth("100.0%");
		innerLayout.addComponent(bottomLayout);
		// bottomLayout.setVisible(false);

		// addAttachment
		addAttachment =
			new Button(Lang.get("add-attachment"), new Button.ClickListener() {

				public void buttonClick(ClickEvent event) {

					addAttachment();
				}

			});
		addAttachment.setStyleName("small");
		addAttachment.setImmediate(true);
		addAttachment.setHeight("-1px");
		addAttachment.setWidth("-1px");
		bottomLayout.addComponent(addAttachment);

		// actionsLayout
		actionsLayout = buildActionsLayout();
		actionsLayout.setStyleName("black");
		actionsLayout.setImmediate(false);
		actionsLayout.setHeight("-1px");
		actionsLayout.setWidth("100.0%");
		mainLayout.addComponent(actionsLayout);

		return mainLayout;
	}

	private HorizontalLayout buildFromLayout() {

		// common part: create layout
		fromLayout = new HorizontalLayout();
		fromLayout.setSpacing(true);

		Label fromLabel = new Label(Lang.get("from"));
		fromLabel.setSizeUndefined();
		fromLayout.addComponent(fromLabel);

		// from
		from = new ComboBox();
		from.setDescription(Lang.get("the-account-to-send-the-message-from"));
		from.setImmediate(true);
		from.setHeight("-1px");
		from.setWidth("100.0%");
		fromLayout.addComponent(from);
		fromLayout.setExpandRatio(from, 1.0f);

		return fromLayout;
	}

	private FormLayout buildRecipientsLayout() {

		// common part: create layout
		recipientsLayout = new FormLayout();
		recipientsLayout.setSpacing(true);

		toField = new TextField();
		toField.setImmediate(false);
		toField.setHeight("-1px");
		toField.setWidth("100.0%");
		toField.setCaption(Lang.get("to"));
		recipientsLayout.addComponent(toField);

		ccField = new TextField();
		ccField.setImmediate(false);
		ccField.setHeight("-1px");
		ccField.setWidth("100.0%");
		ccField.setCaption(Lang.get("cc"));
		recipientsLayout.addComponent(ccField);

		bccField = new TextField();
		bccField.setImmediate(false);
		bccField.setHeight("-1px");
		bccField.setWidth("100.0%");
		bccField.setCaption(Lang.get("bcc"));
		recipientsLayout.addComponent(bccField);

		return recipientsLayout;
	}

	private HorizontalLayout buildSubjectLayout() {

		// common part: create layout
		subjectLayout = new HorizontalLayout();

		// subject
		subject = new TextField();
		subject.setImmediate(false);
		subject.setHeight("-1px");
		subject.setWidth("100.0%");
		subjectLayout.addComponent(subject);

		return subjectLayout;
	}

	private VerticalLayout buildMessageLayout() {

		// common part: create layout
		messageLayout = new VerticalLayout();

		// message
		message = new RichTextArea();
		message.setImmediate(false);
		message.setHeight("100.0%");
		message.setWidth("100.0%");
		messageLayout.addComponent(message);

		return messageLayout;
	}

	private HorizontalLayout buildActionsLayout() {

		// common part: create layout
		actionsLayout = new HorizontalLayout();

		// cancel
		cancel = new Button();
		cancel.setImmediate(true);
		cancel.setHeight("-1px");
		cancel.setWidth("-1px");
		cancel.setCaption(Lang.get("discard"));
		actionsLayout.addComponent(cancel);

		// save
		save = new Button();
		save.setImmediate(true);
		save.setHeight("-1px");
		save.setWidth("-1px");
		save.setCaption("Save");
		actionsLayout.addComponent(save);

		// send
		send = new Button();
		send.setStyleName("primary");
		send.setImmediate(true);
		send.setHeight("-1px");
		send.setWidth("-1px");
		send.setCaption(Lang.get("send"));
		actionsLayout.addComponent(send);

		return actionsLayout;
	}

	// non UI-building follows:

	public String getTo() {
		return (String) toField.getValue();
	}

	public String getCc() {
		return (String) ccField.getValue();
	}

	public String getBcc() {
		return (String) bccField.getValue();
	}

	public void setTo(String value) {
		toField.setValue(value);
	}

	public void setCc(String value) {
		ccField.setValue(value);
	}

	public void setBcc(String value) {
		bccField.setValue(value);
	}

	public List<MailFile> getAttachments() {

		List<MailFile> a = new ArrayList<MailFile>();
		for (AttachmentLayout attachment : attachments) {
			if (attachment.hasFile()) {
				MailFile mf = new MailFile(attachment.getFile(), attachment
						.getFilename(), attachment.getSize());
				a.add(mf);
			}
		}
		return a;
	}

	public void setMessage(String message) {

		this.message.setValue(message);
	}

	public String getMessage() {

		return (String) message.getValue();
	}

	public void setDraftMessageId(long id) {

		draftMessageId = id;
	}

	public long getDraftMessageId() {

		return draftMessageId;
	}

	public void setSubject(String subject) {

		this.subject.setValue(subject);
	}

	public String getSubject() {

		return (String) subject.getValue();
	}

	private void initAccounts() {

		List<Account> accounts = null;
		try {
			accounts = AccountLocalServiceUtil.getAccounts(Controller.get()
					.getUser().getUserId());
		}
		catch (SystemException e) {
			getApplication().getMainWindow().showNotification(
				Lang.get("unable-to-access-account"),
				Notification.TYPE_ERROR_MESSAGE);
			_log.warn("Unable to access account", e);
			fireDiscard();
		}
		BeanItemContainer<Account> bi =
			new BeanItemContainer<Account>(accounts);
		from.setContainerDataSource(bi);
		from.setItemCaptionPropertyId(ACCOUNT_NAME_ID);
		from.setValue(accounts.get(0));
	}

	public Account getFrom() {

		return (Account) from.getValue();
	}

	private void addAttachment() {

		AttachmentLayout attachment = new AttachmentLayout();
		bottomLayout.addComponent(attachment, attachments.size());
		attachments.add(attachment);
	}

	private void removeAttachment(AttachmentLayout attachment) {

		attachment.delete();
		attachments.remove(attachment);
		bottomLayout.removeComponent(attachment);
	}

	public void addListener(ComposerListener listener) {

		listeners.add(listener);
	}

	public void removeListener(ComposerListener listener) {

		listeners.remove(listener);
	}

	private void fireDiscard() {

		for (ComposerListener listener : listeners) {
			listener.messageDiscarded(this);
		}
	}

	private void fireSave(boolean skipPasswordCheck) {
		if (!getFrom().isSavePassword() && !skipPasswordCheck){
			Account account = getFrom();
			String password = null;
			try {
				password = Controller.get().getPasswordRetriever().getPassword(account.getAccountId());
			} catch (PortalException e2) {
			} catch (SystemException e2) {
			}

			if (password != null){
				try {
					Controller.get().getMailManager().storePassword(account.getAccountId(), password);
					Controller.get().getAccountManager().updateAccount(account, Controller.get());
					Controller.get().getMailManager().synchronizeAccount(account.getAccountId());
				} catch (PortalException e1) {
					Controller.get().showError(
							Lang.get("unable-to-synchronize-account"), e1);
				} catch (SystemException e1) {
					Controller.get().showError(
							Lang.get("unable-to-synchronize-account"), e1);
				}
			} else {
				PasswordPrompt prompt = new PasswordPrompt(account);
				prompt.addListener(new Window.CloseListener() {
					//@Override
					public void windowClose(CloseEvent e) {
						PasswordPrompt prompt = (PasswordPrompt)e.getWindow();
						if (prompt.getStatus() == PasswordPrompt.Status.VALIDATED){
							fireSave(true);
						}
					}
				});
				getWindow().addWindow(prompt);
				prompt.center();
				return;
			}
		}

		Message msg = null;
		try {
			msg = MessageUtil.saveOrSendMessage(getFrom(), getTo(), getCc(),
					getBcc(), getSubject(), getMessage(), getAttachments(),
					false, draftMessageId);

			for (ComposerListener listener : listeners) {
				listener.messageSaved(this, msg);
			}
		} catch (MailException me) {
			if (me.getType() == MailException.MESSAGE_HAS_NO_RECIPIENTS){
				getApplication().getMainWindow().showNotification(
						Lang.get("please-specify-at-least-one-recipient"),
						me.getMessage(),Notification.TYPE_ERROR_MESSAGE );
			} else {
				getApplication().getMainWindow().showNotification(
						Lang.get("unable-to-save-draft"), me.getMessage(),
						Notification.TYPE_ERROR_MESSAGE);
			}

			return;
		} catch (PortalException e) {
			_log.error(e);
			getApplication().getMainWindow().showNotification(
					Lang.get("unable-to-save-draft"), e.getMessage(),
					Notification.TYPE_ERROR_MESSAGE);
		} catch (SystemException e) {
			_log.error(e);
			getApplication().getMainWindow().showNotification(
					Lang.get("unable-to-save-draft"), e.getMessage(),
					Notification.TYPE_ERROR_MESSAGE);
		}
	}

	private void fireSend(boolean skipPasswordCheck) {
		if (!getFrom().isSavePassword() && !skipPasswordCheck){
			/*
			 *	Password not saved, try to get it from the password retriever or from the user
			 */
			Account account = getFrom();
			String password = null;
			try {
				password = Controller.get().getPasswordRetriever().getPassword(account.getAccountId());
			} catch (PortalException e2) {
				// Don't do anything, the password prompt will be displayed later
			} catch (SystemException e2) {
				// Don't do anything, the password prompt will be displayed later
			}

			if (password != null){
				try {
					Controller.get().getMailManager().storePassword(account.getAccountId(), password);
					Controller.get().getAccountManager().updateAccount(account, Controller.get());
					Controller.get().getMailManager().synchronizeAccount(account.getAccountId());
				} catch (PortalException e1) {
					Controller.get().showError(
							Lang.get("unable-to-synchronize-account"), e1);
				} catch (SystemException e1) {
					Controller.get().showError(
							Lang.get("unable-to-synchronize-account"), e1);
				}
			} else {
				PasswordPrompt prompt = new PasswordPrompt(account);
				prompt.addListener(new Window.CloseListener() {
					public void windowClose(CloseEvent e) {
						PasswordPrompt prompt = (PasswordPrompt)e.getWindow();
						if (prompt.getStatus() == PasswordPrompt.Status.VALIDATED){
							fireSend(true);
						}
					}
				});
				getWindow().addWindow(prompt);
				prompt.center();
				return;
			}
		}

		Message msg = null;
		try {
			msg = MessageUtil.saveOrSendMessage(getFrom(), getTo(), getCc(),
					getBcc(), getSubject(), getMessage(), getAttachments(),
					true, draftMessageId);

			for (ComposerListener listener : listeners) {
				listener.messageSent(this, msg);
			}
		}
		catch (MailException me) {
			if (me.getType() == MailException.MESSAGE_HAS_NO_RECIPIENTS) {
				getApplication().getMainWindow().showNotification(
						Lang.get("please-specify-at-least-one-recipient"),
						me.getMessage(), Notification.TYPE_ERROR_MESSAGE);
			} else if (me.getType() == MailException.MESSAGE_INVALID_ADDRESS) {
				getApplication().getMainWindow().showNotification(
						Lang.get(
								"please-make-sure-the-following-address-is-properly"
										+ "-formatted", me.getValue()),
						me.getMessage(), Notification.TYPE_ERROR_MESSAGE);

			} else {
				getApplication().getMainWindow().showNotification(
						Lang.get("unable-to-send-message"), me.getMessage(),
						Notification.TYPE_ERROR_MESSAGE);
				return;
			}
		} catch (PortalException e) {
			_log.error(e);
			getApplication().getMainWindow().showNotification(
					Lang.get("unable-to-send-message"), e.getMessage(),
					Notification.TYPE_ERROR_MESSAGE);
		} catch (SystemException e) {
			_log.error(e);
			getApplication().getMainWindow().showNotification(
					Lang.get("unable-to-send-message"), e.getMessage(),
					Notification.TYPE_ERROR_MESSAGE);
		}
	}

	public class AttachmentLayout extends HorizontalLayout {

		private Buffer buffer = new Buffer();

		private Upload upload = new Upload(null, buffer);

		ProgressIndicator progress = new ProgressIndicator();

		private long size = 0;
		private String filename = null;

		AttachmentLayout() {

			setStyleName("blue");
			setSpacing(true);
			setWidth("100.0%");

			addComponent(upload);

			progress.setPollingInterval(500);
			progress.setWidth("100.0%");

			upload.addListener(new Upload.StartedListener() {

				public void uploadStarted(StartedEvent event) {

					addComponent(progress);
					setExpandRatio(progress, 1.0f);
					setComponentAlignment(progress, "middle center");
					// upload.setVisible(false);
					progress.setIndeterminate(false);
				}

			});

			upload.addListener(new Upload.ProgressListener() {

				public void updateProgress(long readBytes, long contentLength) {

					float p = (float) readBytes / contentLength;
					progress.setValue(p);
				}

			});

			upload.addListener(new Upload.FailedListener() {

				public void uploadFailed(FailedEvent event) {

					progress.setVisible(false);
					getWindow().showNotification(
						Lang.get("unable-to-add-attachment"),
						event.getFilename(), Notification.TYPE_ERROR_MESSAGE);
				}

			});

			upload.addListener(new Upload.FinishedListener() {

				public void uploadFinished(FinishedEvent event) {

					if (event.getLength() <= 0) {
						getWindow().showNotification(
							Lang.get("unable-to-add-attachment"),
							event.getFilename(),
							Notification.TYPE_ERROR_MESSAGE);
						removeAttachment(AttachmentLayout.this);
					}
					else {
						size = event.getLength();
						filename = event.getFilename();
						setAttachment(
							event.getFilename(), event.getLength(),
							event.getMIMEType(), buffer);
					}
				}

			});

		}

		public String getFilename() {

			return filename;
		}

		public boolean hasFile() {

			return size > 0;
		}

		public File getFile() {

			return buffer.getFile();
		}

		public long getSize() {

			return size;
		}

		public void delete() {

			buffer.delete();
		}

		private void setAttachment(
			String file, long length, String mime, Buffer buffer) {

			removeAllComponents();

			DecimalFormat df = new DecimalFormat("#########0.0");

			String size = null;
			if (length >= 1000 * 1000 * 1000) {
				size =
					df.format((float) length / (1000 * 1000 * 1000)) + " " +
						Lang.get("gigabytes");
			}
			else if (length >= 1000 * 1000) {
				size =
					df.format((float) length / (1000 * 1000)) + " " +
						Lang.get("megabytes");
			}
			else if (length >= 1000) {
				size =
					df.format((float) length / 1000) + " " +
						Lang.get("kilobytes");
			}
			else {
				size = df.format(length) + " " + Lang.get("bytes");
			}
			StreamResource sr =
				new StreamResource(buffer, file, getApplication());
			final Link l = new Link(file + " (" + size + ") ", sr);
			addComponent(l);

			Button b = new Button("x", new Button.ClickListener() {

				public void buttonClick(ClickEvent event) {

					removeAttachment(AttachmentLayout.this);
				}
			});
			b.setDescription(Lang.get("remove") + file);
			b.setStyleName("small");
			addComponent(b);
			setExpandRatio(b, 1.0f);

		}

	}

	public class Buffer implements StreamResource.StreamSource, Upload.Receiver {

		String mimeType;

		String fileName;

		private File file;

		public Buffer() {

		}

		public InputStream getStream() {

			if (file == null) {
				return null;
			}
			try {
				return new FileInputStream(file);
			}
			catch (final FileNotFoundException e) {
				getWindow().showNotification(
					Lang.get("unable-to-add-attachment"),
					Notification.TYPE_ERROR_MESSAGE);
				_log.warn("Unable to attach files", e);
			}
			return null;
		}

		/**
		 * @see com.vaadin.ui.Upload.Receiver#receiveUpload(String, String)
		 */
		public OutputStream receiveUpload(String filename, String MIMEType) {

			fileName = filename;
			mimeType = MIMEType;

			if (file != null) {
				file.delete();
			}

			try {
				file = File.createTempFile("attachment", null);
			}
			catch (final IOException e) {
				getWindow().showNotification(
					Lang.get("unable-to-add-attachment"), filename,
					Notification.TYPE_ERROR_MESSAGE);
				_log.warn("Unable to attach files", e);
			}

			try {
				return new FileOutputStream(file);
			}
			catch (final FileNotFoundException e) {
				getWindow().showNotification(
					Lang.get("unable-to-add-attachment"), filename,
					Notification.TYPE_ERROR_MESSAGE);
				_log.warn("Unable to attach files", e);
			}
			return null;
		}

		/**
		 * Returns the fileName.
		 *
		 * @return String
		 */
		public String getFileName() {

			return fileName;
		}

		public File getFile() {

			return file;
		}

		/**
		 * Returns the mimeType.
		 *
		 * @return String
		 */
		public String getMimeType() {

			return mimeType;
		}

		public boolean delete() {

			if (file != null) {
				return file.delete();
			}
			// no file to delete - that's a success in our book
			return true;
		}

	}

	@Override
	protected void finalize()
		throws Throwable {

		for (AttachmentLayout attachment : attachments) {
			attachment.delete();
		}
	}

	public interface ComposerListener {

		public void messageDiscarded(Composer composer);

		public void messageSaved(Composer composer, Message message);

		public void messageSent(Composer composer, Message message);
	}

	public void focusToField(){
		toField.focus();
	}

	public void focusCCField(){
		ccField.focus();
	}

	public void focusBCCField(){
		bccField.focus();
	}

	public void focusSubject(){
		subject.focus();
	}

	public void focusBody(){
		message.focus();
	}

}