/*
 * LinShare is an open source filesharing software, part of the LinPKI software
 * suite, developed by Linagora.
 * 
 * Copyright (C) 2015 LINAGORA
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version, provided you comply with the Additional Terms applicable for
 * LinShare software by Linagora pursuant to Section 7 of the GNU Affero General
 * Public License, subsections (b), (c), and (e), pursuant to which you must
 * notably (i) retain the display of the “LinShare™” trademark/logo at the top
 * of the interface window, the display of the “You are using the Open Source
 * and free version of LinShare™, powered by Linagora © 2009–2015. Contribute to
 * Linshare R&D by subscribing to an Enterprise offer!” infobox and in the
 * e-mails sent with the Program, (ii) retain all hypertext links between
 * LinShare and linshare.org, between linagora.com and Linagora, and (iii)
 * refrain from infringing Linagora intellectual property rights over its
 * trademarks and commercial brands. Other Additional Terms apply, see
 * <http://www.linagora.com/licenses/> for more details.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License and
 * its applicable Additional Terms for LinShare along with this program. If not,
 * see <http://www.gnu.org/licenses/> for the GNU Affero General Public License
 * version 3 and <http://www.linagora.com/licenses/> for the Additional Terms
 * applicable to LinShare software.
 */

package org.linagora.linshare.core.facade.webservice.common.dto;

import javax.xml.bind.annotation.XmlRootElement;

import org.linagora.linshare.core.exception.BusinessException;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "FineUploader")
@ApiModel(value = "FineUploader", description = "A response for FineUploader")
public class FineUploaderDto {

	@ApiModelProperty(value = "Flag to notify FineUploader of the success of the upload", required = true)
	private boolean success;

	@ApiModelProperty(value = "Uuid of the file, generated by the server and given back to FineUploader")
	private String newUuid;

	@ApiModelProperty(value = "Error message")
	private String error;

	@ApiModelProperty(value = "True if async upload is enabled")
	private Boolean async;

	@ApiModelProperty(value = "The delay between every request to ask if upload is complete.")
	private Integer frequence;

	@ApiModelProperty(value = "Filename")
	private String filename;

	public FineUploaderDto(boolean success, String newUuid, String filename) {
		super();
		this.success = success;
		this.newUuid = newUuid;
		this.error = null;
		this.async = false;
		this.frequence = 5;
		this.filename = filename;
	}

	public FineUploaderDto(AsyncTaskDto asyncTaskDto) {
		this(true, asyncTaskDto.getUuid(), asyncTaskDto.getFileName());
		this.async = true;
	}

	public FineUploaderDto(BusinessException exception) {
		super();
		this.success = false;
		this.newUuid = null;
		this.error = exception.getErrorCode().name();
		this.async = false;
	}

	public FineUploaderDto(Exception exception) {
		super();
		this.success = false;
		this.newUuid = null;
		this.error = exception.getMessage();
		if (exception instanceof BusinessException) {
			this.error = ((BusinessException)exception).getErrorCode().name();
		}
		this.async = false;
	}

	public FineUploaderDto(String error) {
		super();
		this.success = false;
		this.newUuid = null;
		this.error = error;
		this.async = false;
	}

	public FineUploaderDto(boolean success, String filename) {
		this(success, "", filename);
		this.async = false;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getNewUuid() {
		return newUuid;
	}

	public void setNewUuid(String newUuid) {
		this.newUuid = newUuid;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Boolean getAsync() {
		return async;
	}

	public void setAsync(Boolean async) {
		this.async = async;
	}

	public Integer getFrequence() {
		return frequence;
	}

	public void setFrequence(Integer frequence) {
		this.frequence = frequence;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
